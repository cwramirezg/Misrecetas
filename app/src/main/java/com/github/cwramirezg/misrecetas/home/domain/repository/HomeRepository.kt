package com.github.cwramirezg.misrecetas.home.domain.repository

import com.github.cwramirezg.misrecetas.home.domain.model.Receta
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getAllRecetas(): Flow<List<Receta>>
}