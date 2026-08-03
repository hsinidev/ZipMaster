package com.example.zipmaster.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ArchiveJobEntity::class], version = 1, exportSchema = false)
abstract class ZipDatabase : RoomDatabase() {
    abstract fun zipDao(): ZipDao
}
