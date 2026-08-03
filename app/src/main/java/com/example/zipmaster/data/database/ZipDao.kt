package com.example.zipmaster.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ZipDao {
    @Query("SELECT * FROM archive_history ORDER BY timestamp DESC")
    fun getHistory(): Flow<List<ArchiveJobEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJob(job: ArchiveJobEntity)

    @Query("DELETE FROM archive_history")
    suspend fun clearHistory()
}
