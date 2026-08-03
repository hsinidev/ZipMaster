package com.example.zipmaster.domain.repository

import android.net.Uri
import com.example.zipmaster.domain.model.ArchiveJob
import com.example.zipmaster.domain.model.EntitlementState
import kotlinx.coroutines.flow.Flow

interface ZipRepository {
    fun getHistory(): Flow<List<ArchiveJob>>
    suspend fun saveJob(job: ArchiveJob)
    fun getEntitlementState(): Flow<EntitlementState>
    suspend fun purchasePremium(): Boolean
    suspend fun compressFiles(files: List<Uri>, archiveName: String, format: String): Flow<Float>
    suspend fun extractArchive(archiveUri: Uri, targetPath: String): Flow<Float>
}
