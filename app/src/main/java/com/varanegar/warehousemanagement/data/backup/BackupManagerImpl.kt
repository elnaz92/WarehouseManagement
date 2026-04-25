//package com.varanegar.warehousemanagement.data.backup
//
//import android.annotation.SuppressLint
//import android.content.Context
//import android.os.Build
//import android.os.Environment
//import android.provider.Settings
//import androidx.datastore.dataStoreFile
//import com.varanegar.warehousemanagement.data.repos.AppDataRepository
//import com.varanegar.warehousemanagement.data.repos.CenterRepository
//import com.varanegar.warehousemanagement.data.repos.StockRepository
//import com.varanegar.warehousemanagement.BuildConfig
//import com.varanegar.warehousemanagement.core.utils.AppInfoUtils
//import com.varanegar.warehousemanagement.data.room.base.PdaDatabase
//import com.varanegar.warehousemanagement.core.utils.logging.FileLoggingTree
//import com.varanegar.warehousemanagement.core.utils.serializers.AppJson
//import com.varanegar.warehousemanagement.data.models.LastUpdateProto
//import com.varanegar.warehousemanagement.data.models.SettingsProto
//import com.varanegar.warehousemanagement.data.models.TokenProto
//import com.varanegar.warehousemanagement.data.models.UserProto
//import dagger.hilt.android.qualifiers.ApplicationContext
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.flow.first
//import kotlinx.coroutines.withContext
//import timber.log.Timber
//import java.io.*
//import java.text.SimpleDateFormat
//import java.util.*
//import java.util.zip.ZipEntry
//import java.util.zip.ZipInputStream
//import java.util.zip.ZipOutputStream
//import javax.inject.Inject
//
//
//class BackupManagerImpl @Inject constructor(
//    @param:ApplicationContext private val context: Context,
//    private val appDataRepo: AppDataRepository,
//    private val centerRepo: CenterRepository,
//    private val stockRepo: StockRepository
//) : BackupManager {
//    private val DB_NAME = "pda"
//    private val DB_SHM = "pda-shm"
//    private val DB_WAL = "pda-wal"
//    private val SETTING_DS_NAME = "settings.json"
//    private val TOKEN_DS_NAME = "token.json"
//    private val USER_DS_NAME = "user.json"
//    private val LAST_UPDATE_DS_NAME = "lastUpdate.json"
//    private val BACKUP_INFO_NAME = "backup.json"
//    private val BACKUP_FOLDER_NAME = "backups"
//
//    override suspend fun backup(): Boolean = withContext(Dispatchers.IO) {
//        try {
//            val folder = createBackupFolder() ?: return@withContext false
//            val destinationZipFile = File(folder, getBackupName())
//            val filesToZip = mutableListOf<File>()
//
//            listOf(DB_NAME, DB_SHM, DB_WAL).forEach { dbName ->
//                val dbFile = context.getDatabasePath(dbName)
//                if (dbFile.exists()) filesToZip.add(dbFile)
//            }
//
//            listOf(
//                SETTING_DS_NAME,
//                TOKEN_DS_NAME,
//                USER_DS_NAME,
//                LAST_UPDATE_DS_NAME
//            ).forEach { dsName ->
//                val dsFile = context.dataStoreFile(dsName)
//                if (dsFile.exists()) filesToZip.add(dsFile)
//            }
//
//            val logDir = File(FileLoggingTree.getLogDirectory(context))
//            if (logDir.exists()) {
//                logDir.listFiles { _, name -> name.endsWith(".log") }
//                    ?.forEach { filesToZip.add(it) }
//            }
//
//            val backupInfo = createBackupInfo()
//            val backupInfoFile = File(context.cacheDir, BACKUP_INFO_NAME)
//            backupInfoFile.writeText(AppJson.encodeToString(backupInfo))
//            filesToZip.add(backupInfoFile)
//
//            zipFilesDirectly(filesToZip, destinationZipFile)
//            if (backupInfoFile.exists()) backupInfoFile.delete()
//            return@withContext true
//        } catch (e: Exception) {
//            Timber.e(e, "Error during backup")
//            return@withContext false
//        }
//    }
//
//    override suspend fun restore(file: File): Boolean = withContext(Dispatchers.IO) {
//        try {
//            var tokenFileStream: ByteArray? = null
//
//            ZipInputStream(FileInputStream(file)).use { zis ->
//                var ze: ZipEntry?
//                while (zis.nextEntry.also { ze = it } != null) {
//                    val entryName = ze?.name ?: continue
//
//                    when (entryName) {
//                        DB_NAME, DB_SHM, DB_WAL -> {
//                            val dbPath = context.getDatabasePath(entryName)
//                            saveStreamToFile(zis, dbPath)
//                        }
//
//                        SETTING_DS_NAME -> saveToSettingDataStoreFile(zis)
//                        USER_DS_NAME -> saveToUserDataStoreFile(zis)
//                        LAST_UPDATE_DS_NAME -> saveToLastUpdateDataStoreFile(zis)
//                        TOKEN_DS_NAME -> tokenFileStream = zis.readBytes()
//                    }
//                    zis.closeEntry()
//                }
//            }
//
//            // restore token at the end
//            tokenFileStream?.let { bytes ->
//                ByteArrayInputStream(bytes).use { saveToTokenDataStoreFile(it) }
//            }
//
//            return@withContext true
//        } catch (e: Exception) {
//            Timber.e(e, "Error during restore")
//            return@withContext false
//        }
//    }
//
//    override fun getList(): List<File> {
//        val path = getExternalFilesDir(context, BACKUP_FOLDER_NAME)?.path ?: return emptyList()
//        val directory = File(path)
//        return directory.listFiles { _, name -> name.endsWith(".backup") }?.sorted() ?: emptyList()
//    }
//
//    @Throws(Exception::class)
//    override suspend fun createBackupInfo(): BackupInfo = withContext(Dispatchers.IO) {
//        val centerId = appDataRepo.centerId.first()
//        val stockId = appDataRepo.stockId.first()
//
//        BackupInfo(
//            date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(Date()),
//            packageName = context.packageName,
//            userName = appDataRepo.user.first().userName,
//            centerId = centerId,
//            centerName = centerRepo.getCenter(centerId)?.dcName,
//            stockId = stockId,
//            stockName = stockRepo.getItem(stockId)?.stockName,
//            appVersionCode = AppInfoUtils.getVersionCode(context),
//            appVersionName = AppInfoUtils.getVersionName(context),
//            deviceModel = Build.MODEL,
//            deviceId = getDeviceId(),
//            deviceSdk = Build.VERSION.SDK_INT,
//            deviceBrand = Build.BRAND,
//            deviceManufacturer = Build.MANUFACTURER,
//            databaseVersion = PdaDatabase.DATABASE_VERSION
//        )
//    }
//
//    override suspend fun getBackupInfo(zipFile: String?): BackupInfo? =
//        withContext(Dispatchers.IO) {
//            if (zipFile.isNullOrEmpty()) return@withContext null
//
//            try {
//                ZipInputStream(FileInputStream(zipFile)).use { zis ->
//                    var ze: ZipEntry?
//                    while (zis.nextEntry.also { ze = it } != null) {
//                        if (ze?.name == BACKUP_INFO_NAME) {
//                            val jsonString = zis.bufferedReader().readText()
//                            return@withContext AppJson.decodeFromString<BackupInfo>(jsonString)
//                        }
//                        zis.closeEntry()
//                    }
//                }
//            } catch (e: Exception) {
//                Timber.e(e, "Error reading backup info")
//            }
//            return@withContext null
//        }
//
//    override suspend fun clearOldBackups() = withContext(Dispatchers.IO) {
//        val max = 30
//        val backupFiles = getList()
//        if (backupFiles.size > max) {
//            backupFiles.take(backupFiles.size - max).forEach {
//                it.delete()
//            }
//        }
//    }
//
//    override fun createBackupFolder(): File? {
//        return getExternalFilesDir(context, BACKUP_FOLDER_NAME)
//    }
//
//    @Throws(Exception::class)
//    private suspend fun zipFilesDirectly(files: List<File>, zipFile: File) =
//        withContext(Dispatchers.IO) {
//            ZipOutputStream(BufferedOutputStream(FileOutputStream(zipFile))).use { zos ->
//                for (file in files) {
//                    if (!file.exists()) continue
//                    try {
//                        FileInputStream(file).use { fis ->
//                            val entry = ZipEntry(file.name)
//                            zos.putNextEntry(entry)
//                            fis.copyTo(zos) // جایگزین حلقه while قدیمی
//                            zos.closeEntry()
//                        }
//                    } catch (e: Exception) {
//                        Timber.e(e, "Error zipping file: ${file.name}")
//                    }
//                }
//            }
//        }
//
//    @Throws(Exception::class)
//    private suspend fun saveStreamToFile(inputStream: InputStream, destFile: File) =
//        withContext(Dispatchers.IO) {
//            destFile.parentFile?.mkdirs()
//            if (!destFile.exists()) destFile.createNewFile()
//
//            FileOutputStream(destFile).use { fos ->
//                inputStream.copyTo(fos)
//            }
//        }
//
//    @SuppressLint("HardwareIds")
//    private fun getDeviceId(): String {
//        return try {
//            Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID) ?: "Unknown"
//        } catch (e: Exception) {
//            "Unknown"
//        }
//    }
//
//    private fun getBackupName(): String {
//        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(Date())
//        return "$timestamp.backup"
//    }
//
//    private fun getExternalFilesDir(context: Context, folderName: String?): File? {
//        val externalDir = context.getExternalFilesDir(null)
//        val file = if (folderName == null) {
//            externalDir
//        } else {
//            File(externalDir, folderName)
//        }
//        if (file != null && !file.exists()) file.mkdirs()
//        return file
//    }
//
//    private fun getAbsoluteFile(relativePath: String, context: Context): File {
//        val state = Environment.getExternalStorageState()
//        return if (Environment.MEDIA_MOUNTED == state) {
//            File(getExternalFilesDir(context, null), relativePath)
//        } else {
//            File(context.filesDir, relativePath)
//        }
//    }
//
//    private suspend fun saveToSettingDataStoreFile(inputStream: InputStream) {
//        val content = inputStream.readBytes().decodeToString()
//        val settingProtoModel = AppJson.decodeFromString<SettingsProto>(content)
//        appDataRepo.setIp(settingProtoModel.ip)
//        appDataRepo.setAccYear(settingProtoModel.accYear)
//        appDataRepo.setCenter(settingProtoModel.centerId)
//        appDataRepo.setStock(settingProtoModel.stockId)
//        appDataRepo.setStockCountCalculatorTabPosition(settingProtoModel.stockCountCalculatorTabPosition)
//    }
//
//    private suspend fun saveToTokenDataStoreFile(inputStream: InputStream) {
//        val content = inputStream.readBytes().decodeToString()
//        val tokenProtoModel = AppJson.decodeFromString<TokenProto>(content)
//        appDataRepo.setToken(
//            accessToken = tokenProtoModel.accessToken,
//            expiresAt = tokenProtoModel.expiresAt
//        )
//    }
//
//    private suspend fun saveToUserDataStoreFile(inputStream: InputStream) {
//        val content = inputStream.readBytes().decodeToString()
//        val userProtoModel = AppJson.decodeFromString<UserProto>(content)
//        appDataRepo.setUserName(userProtoModel.userName)
//        appDataRepo.setLoginDate(userProtoModel.loginDate)
//    }
//
//    private suspend fun saveToLastUpdateDataStoreFile(inputStream: InputStream) {
//        val content = inputStream.readBytes().decodeToString()
//        val lastUpdateProtoModel = AppJson.decodeFromString<LastUpdateProto>(content)
//        appDataRepo.setStockCountLastUpdate(lastUpdateProtoModel.stockCountLastUpdate)
//        appDataRepo.setProductBarcodeLastUpdate(lastUpdateProtoModel.productBarcodeLastUpdate)
//        appDataRepo.setVoucherHeaderLastUpdate(lastUpdateProtoModel.voucherHeaderLastUpdate)
//    }
//}