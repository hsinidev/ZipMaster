package com.example.zipmaster.domain.usecase

import android.net.Uri
import com.example.zipmaster.domain.repository.ZipRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExtractArchiveUseCase @Inject constructor(
    private val repository: ZipRepository
) {
    suspend operator fun invoke(archiveUri: Uri, targetPath: String): Flow<Float> {
        return repository.extractArchive(archiveUri, targetPath)
    }
}
