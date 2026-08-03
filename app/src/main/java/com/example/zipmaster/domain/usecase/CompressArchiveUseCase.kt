package com.example.zipmaster.domain.usecase

import android.net.Uri
import com.example.zipmaster.domain.repository.ZipRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CompressArchiveUseCase @Inject constructor(
    private val repository: ZipRepository
) {
    suspend operator fun invoke(files: List<Uri>, name: String, format: String): Flow<Float> {
        return repository.compressFiles(files, name, format)
    }
}
