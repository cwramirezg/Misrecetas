package com.github.cwramirezg.misrecetas.home.domain.model

data class Receta(
    val id: String,
    val nombre: String,
    val descripcion: String,
    val localizacion: String,
    val gps: String,
    val urlImagen: String
)