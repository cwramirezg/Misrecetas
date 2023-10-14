package com.github.cwramirezg.misrecetas.home.domain.map.usecase

import com.github.cwramirezg.misrecetas.home.domain.model.Receta
import com.github.cwramirezg.misrecetas.home.domain.repository.HomeRepository

class GetRecetaByIdUseCase(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(id: String): Receta {
        return repository.getRecetaById(id)
    }
}