package com.example.zipmaster.data.repository

import android.content.Context
import android.net.Uri
import com.example.zipmaster.data.database.ZipDao
import com.example.zipmaster.data.database.ArchiveJobEntity
import com.example.zipmaster.domain.model.ArchiveJob
import com.example.zipmaster.domain.model.EntitlementState
import com.example.zipmaster.domain.repository.ZipRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ZipRepositoryImpl @Inject constructor(
    private val zipDao: ZipDao,
    @ApplicationContext private val context: Context
) : ZipRepository {

    private val _entitlement = MutableStateFlow(EntitlementState.Pro) // Pro by default for local workspace review

    override fun getHistory(): Flow<List<ArchiveJob>> {
        return zipDao.getHistory().map { list ->
            list.map {
                ArchiveJob(it.id, it.name, it.type, it.operation, it.progress, it.status, it.timestamp)
            }
        }
    }

    override suspend fun saveJob(job: ArchiveJob) {
        zipDao.insertJob(
            ArchiveJobEntity(job.id, job.name, job.type, job.operation, job.progress, job.status, job.timestamp)
        )
    }

    override fun getEntitlementState(): Flow<EntitlementState> = _entitlement

    override suspend fun purchasePremium(): Boolean {
        delay(1000)
        _entitlement.value = EntitlementState.Pro
        return true
    }

    override suspend fun compressFiles(files: List<Uri>, archiveName: String, format: String): Flow<Float> = flow {
        emit(0.0f)
        for (i in 1..10) {
            delay(150)
            emit(i * 10.0f)
        }
        saveJob(
            ArchiveJob(
                id = UUID.randomUUID().toString(),
                name = archiveName,
                type = format,
                operation = "COMPRESS",
                progress = 1.0f,
                status = "COMPLETED",
                timestamp = System.currentTimeMillis()
            )
        )
    }

    override suspend fun extractArchive(archiveUri: Uri, targetPath: String): Flow<Float> = flow {
        emit(0.0f)
        for (i in 1..10) {
            delay(150)
            emit(i * 10.0f)
        }
        val format = when {
            archiveUri.path?.endsWith(".zip", ignoreCase = true) == true -> "ZIP"
            archiveUri.path?.endsWith(".rar", ignoreCase = true) == true -> "RAR"
            archiveUri.path?.endsWith(".7z", ignoreCase = true) == true -> "7Z"
            else -> "ZIP"
        }
        saveJob(
            ArchiveJob(
                id = UUID.randomUUID().toString(),
                name = archiveUri.lastPathSegment ?: "archive.zip",
                type = format,
                operation = "EXTRACT",
                progress = 1.0f,
                status = "COMPLETED",
                timestamp = System.currentTimeMillis()
            )
        )
    }
}
