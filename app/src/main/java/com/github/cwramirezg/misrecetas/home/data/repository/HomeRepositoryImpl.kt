package com.github.cwramirezg.misrecetas.home.data.repository

import com.github.cwramirezg.misrecetas.home.data.local.HomeDao
import com.github.cwramirezg.misrecetas.home.data.mapper.toDomain
import com.github.cwramirezg.misrecetas.home.data.mapper.toLocal
import com.github.cwramirezg.misrecetas.home.data.remote.HomeApi
import com.github.cwramirezg.misrecetas.home.domain.model.Receta
import com.github.cwramirezg.misrecetas.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HomeRepositoryImpl(
    private val dao: HomeDao,
    private val api: HomeApi
) : HomeRepository {
    override fun getAllRecetas(): Flow<List<Receta>> {
        return dao.getAllRecetas().map { recetas -> recetas.map { it.toDomain() } }
    }

   override suspend fun getRecetas(): List<Receta> {
        return api.getRecetas().toDomain()
    }

    override suspend fun insertRecetas(recetas: List<Receta>) {
        dao.insertReceta(recetas.map { it.toLocal() })
    }

    override suspend fun getRecetaById(id: String): Receta {
        return dao.getRecetaById(id).toDomain()
    }

    override suspend fun countRecetas(): Int {
        return dao.countRecetas()
    }
}