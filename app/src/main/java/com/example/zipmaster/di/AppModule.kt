package com.example.zipmaster.di

import android.content.Context
import androidx.room.Room
import com.example.zipmaster.data.database.ZipDao
import com.example.zipmaster.data.database.ZipDatabase
import com.example.zipmaster.data.repository.ZipRepositoryImpl
import com.example.zipmaster.domain.repository.ZipRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ZipDatabase {
        return Room.databaseBuilder(
            context,
            ZipDatabase::class.java,
            "zipmaster_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideDao(db: ZipDatabase): ZipDao = db.zipDao()

    @Provides
    @Singleton
    fun provideRepository(impl: ZipRepositoryImpl): ZipRepository = impl
}
