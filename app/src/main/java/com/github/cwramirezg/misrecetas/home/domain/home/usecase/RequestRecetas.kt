package com.github.cwramirezg.misrecetas.home.domain.home.usecase

import com.github.cwramirezg.misrecetas.home.domain.repository.HomeRepository

class RequestRecetas(
    private val repository: HomeRepository
) {
   suspend operator fun invoke() {
        val isEmpty = repository.countRecetas() == 0
        if (isEmpty){
            repository.insertRecetas(repository.getRecetas())
        }
    }
}