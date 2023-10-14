package com.github.cwramirezg.misrecetas.home.data.remote

import com.github.cwramirezg.misrecetas.home.data.remote.dto.RecetaResponse
import retrofit2.http.GET

interface HomeApi {
    companion object {
        const val BASE_URL = "https://demo4909567.mockable.io"
    }

    @GET("/receta")
    suspend fun getRecetas(): RecetaResponse
}