package com.github.cwramirezg.misrecetas.home.ui.map

import com.github.cwramirezg.misrecetas.home.domain.model.Receta

data class MapState(
    val receta: Receta = Receta()
)