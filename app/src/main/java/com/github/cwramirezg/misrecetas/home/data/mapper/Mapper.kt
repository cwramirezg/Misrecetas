package com.github.cwramirezg.misrecetas.home.data.mapper

import com.github.cwramirezg.misrecetas.home.data.remote.dto.RecetaResponse
import com.github.cwramirezg.misrecetas.home.domain.model.Receta

fun RecetaResponse.toDomain(): Receta {
    return Receta(
        id = id,
        nombre = nombre,
        descripcion = descripcion,
        localizacion = localizacion,
        gps = gps,
        urlImagen = urlImagen
    )
}

fun List<RecetaResponse>.toDomain(): List<Receta> {
    return this.map { it.toDomain() }
}