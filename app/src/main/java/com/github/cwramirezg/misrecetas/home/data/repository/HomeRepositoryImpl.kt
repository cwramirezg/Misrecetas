package com.github.cwramirezg.misrecetas.home.data.repository

import com.github.cwramirezg.misrecetas.home.data.mapper.toDomain
import com.github.cwramirezg.misrecetas.home.data.remote.Api
import com.github.cwramirezg.misrecetas.home.domain.model.Receta
import com.github.cwramirezg.misrecetas.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRepositoryImpl(
    private val api: Api
) : HomeRepository {
    override fun getAllRecetas(): Flow<List<Receta>> {
        return flow {
            val recetas = api.getRecetas().toDomain()
            emit(recetas)
        }
    }
}