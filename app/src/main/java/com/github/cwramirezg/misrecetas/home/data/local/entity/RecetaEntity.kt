package com.github.cwramirezg.misrecetas.home.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecetaEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val nombre: String,
    val descripcion: String,
    val localizacion: String,
    val lat: Double,
    val lon: Double,
    val urlImagen: String
)