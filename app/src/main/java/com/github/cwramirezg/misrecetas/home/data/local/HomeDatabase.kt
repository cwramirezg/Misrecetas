package com.github.cwramirezg.misrecetas.home.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.cwramirezg.misrecetas.home.data.local.entity.RecetaEntity

@Database(entities = [RecetaEntity::class], version = 1)
abstract class HomeDatabase : RoomDatabase() {
    abstract val dao: HomeDao
}