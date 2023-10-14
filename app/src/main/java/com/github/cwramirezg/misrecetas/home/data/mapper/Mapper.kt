package com.github.cwramirezg.misrecetas.home.data.mapper

import com.github.cwramirezg.misrecetas.home.data.local.entity.RecetaEntity
import com.github.cwramirezg.misrecetas.home.data.remote.dto.RecetaResponse
import com.github.cwramirezg.misrecetas.home.data.remote.dto.Respuesta
import com.github.cwramirezg.misrecetas.home.domain.model.Receta

fun Respuesta.toDomain(): Receta {
    return Receta(
        id = id,
        nombre = nombre,
        descripcion = descripcion,
        localizacion = localizacion,
        gps = gps,
        urlImagen = urlImagen
    )
}

fun RecetaResponse.toDomain(): List<Receta> {
    return this.respuesta.map { it.toDomain() }
}

fun RecetaEntity.toDomain(): Receta {
    return Receta(
        id = id,
        nombre = nombre,
        descripcion = descripcion,
        localizacion = localizacion,
        gps = gps,
        urlImagen = urlImagen
    )
}

fun Receta.toLocal(): RecetaEntity {
    return RecetaEntity(
        id = id,
        nombre = nombre,
        descripcion = descripcion,
        localizacion = localizacion,
        gps = gps,
        urlImagen = urlImagen
    )
}
