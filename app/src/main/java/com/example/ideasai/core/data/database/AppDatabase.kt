package com.example.ideasai.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ideasai.core.data.database.dao.IdeaDao
import com.example.ideasai.core.data.database.entity.IdeaEntity

@Database(
    entities = [
        IdeaEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ideaDao(): IdeaDao
}