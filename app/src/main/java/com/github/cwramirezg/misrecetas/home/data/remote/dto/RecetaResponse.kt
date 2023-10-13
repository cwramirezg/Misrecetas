package com.github.cwramirezg.misrecetas.home.data.remote.dto

import com.squareup.moshi.Json

data class RecetaResponse(
    @Json(name = "id") val id: String,
    @Json(name = "nombre") val nombre: String,
    @Json(name = "descripcion") val descripcion: String,
    @Json(name = "localizacion") val localizacion: String,
    @Json(name = "gps") val gps: String,
    @Json(name = "urlImagen") val urlImagen: String
)