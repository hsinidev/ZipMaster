package com.example.zipmaster.domain.model

import android.net.Uri

data class ArchiveJob(
    val id: String,
    val name: String,
    val type: String, // ZIP, RAR, 7Z, TAR, GZ
    val operation: String, // COMPRESS, EXTRACT
    val progress: Float,
    val status: String, // COMPLETED, PROCESSING, FAILED
    val timestamp: Long
)

enum class EntitlementState {
    Free, Pro, Pending, Unknown
}
