package com.github.cwramirezg.misrecetas.home.domain.model

import androidx.room.Ignore

data class Receta(
    val id: String,
    val nombre: String,
    val descripcion: String,
    val localizacion: String,
    val gps: String,
    val urlImagen: String
) {
    @Ignore
    constructor() : this(
        id = "",
        nombre = "",
        descripcion = "",
        localizacion = "",
        gps = "",
        urlImagen = ""
    )
}