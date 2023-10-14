package com.github.cwramirezg.misrecetas.home.domain.model

import androidx.room.Ignore

data class Receta(
    val id: String,
    val nombre: String,
    val descripcion: String,
    val localizacion: String,
    val lat: Double,
    val lon: Double,
    val urlImagen: String
) {
    @Ignore
    constructor() : this(
        id = "",
        nombre = "",
        descripcion = "",
        localizacion = "",
        lat = 0.0,
        lon = 0.0,
        urlImagen = ""
    )
}