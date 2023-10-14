package com.github.cwramirezg.misrecetas.home.ui.home

import com.github.cwramirezg.misrecetas.home.domain.model.Receta

data class HomeState(
    val loading: Boolean = false,
    val recetas: List<Receta> = emptyList()
)