package com.github.cwramirezg.misrecetas.home.data.remote.dto

import com.squareup.moshi.Json

data class RecetaResponse(
    @Json(name = "respuesta") val respuesta: List<Respuesta>,
    @Json(name = "mensaje") val mensaje: String,
    @Json(name = "codigoOperacion") val codigoOperacion: String
)

data class Respuesta(
    @Json(name = "id") val id: String,
    @Json(name = "nombre") val nombre: String,
    @Json(name = "descripcion") val descripcion: String,
    @Json(name = "localizacion") val localizacion: String,
    @Json(name = "gps") val gps: String,
    @Json(name = "urlImagen") val urlImagen: String
)