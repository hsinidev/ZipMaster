package com.example.zipmaster.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "archive_history")
data class ArchiveJobEntity(
    @PrimaryKey val id: String,
    val name: String,
    val type: String,
    val operation: String,
    val progress: Float,
    val status: String,
    val timestamp: Long
)
