package com.github.cwramirezg.misrecetas.home.ui.detail

import com.github.cwramirezg.misrecetas.home.domain.model.Receta

data class DetailState(
    val receta: Receta = Receta()
)