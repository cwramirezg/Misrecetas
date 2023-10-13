package com.github.cwramirezg.misrecetas.home.ui.home

import com.github.cwramirezg.misrecetas.home.domain.model.Receta

sealed interface HomeEvent {
    data class OnClickReceta(val receta: Receta) : HomeEvent
}