package com.github.cwramirezg.misrecetas.home.domain.repository

import com.github.cwramirezg.misrecetas.home.domain.model.Receta
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getAllRecetas(): Flow<List<Receta>>
    suspend fun getRecetas(): List<Receta>
    suspend fun insertRecetas(recetas: List<Receta>)
    suspend fun getRecetaById(id: String): Receta
    suspend fun countRecetas(): Int
}