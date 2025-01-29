package com.example.ideasai.di

import android.content.Context
import androidx.room.Room
import com.example.ideasai.core.data.database.AppDatabase
import com.example.ideasai.core.data.database.IoDispatcher
import com.example.ideasai.core.data.database.dao.IdeaDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room
        .databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            name = "ideas_database"
        )
        .build()

    @Provides
    @Singleton
    fun provideIdeaDao(
        appDatabase: AppDatabase
    ): IdeaDao = appDatabase.ideaDao()

    @Provides
    @IoDispatcher
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

}