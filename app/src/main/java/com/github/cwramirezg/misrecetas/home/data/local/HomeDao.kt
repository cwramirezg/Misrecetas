package com.github.cwramirezg.misrecetas.home.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.cwramirezg.misrecetas.home.data.local.entity.RecetaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReceta(recetaEntity: RecetaEntity)

    @Query("SELECT * FROM RecetaEntity WHERE id = :id LIMIT 1")
    fun getRecetaById(id: String): RecetaEntity

    @Query("SELECT * FROM RecetaEntity")
    fun getAllRecetas(): Flow<List<RecetaEntity>>

    @Query("SELECT COUNT(*) FROM RecetaEntity")
    fun countRecetas(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReceta(recetas: List<RecetaEntity>)
}