package com.github.cwramirezg.misrecetas.home.domain.home.usecase

import com.github.cwramirezg.misrecetas.home.domain.model.Receta
import com.github.cwramirezg.misrecetas.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class GetRecetasUseCase(
    private val repository: HomeRepository
) {
    operator fun invoke(): Flow<List<Receta>> {
        return repository.getAllRecetas()
    }
}