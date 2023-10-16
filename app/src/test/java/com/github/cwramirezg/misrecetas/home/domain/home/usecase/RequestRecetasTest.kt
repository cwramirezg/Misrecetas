package com.github.cwramirezg.misrecetas.home.domain.home.usecase

import com.github.cwramirezg.misrecetas.home.data.repository.HomeRepositoryImpl
import com.github.cwramirezg.misrecetas.home.domain.model.Receta
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verifyBlocking

class RequestRecetasTest {

    private lateinit var requestRecetas: RequestRecetas

    @Test
    fun `Si no hay recetas, se llama al servicio y se insertan`() {
        val recetas =
            listOf(Receta("1", "nombre", "descripcion", "localizacion", 0.0, 0.0, "urlImagen"))
        val homeRepository = mock<HomeRepositoryImpl> {
            onBlocking { countRecetas() } doReturn 0
            onBlocking { getRecetas() } doReturn recetas
        }
        requestRecetas = RequestRecetas(homeRepository)
        runBlocking { requestRecetas.invoke() }
        verifyBlocking(homeRepository) { insertRecetas(recetas) }
        verifyBlocking(homeRepository) { getRecetas() }
    }

    @Test
    fun `Si hay recetas, no se llama al servicio y no se insertan`() {
        val recetas =
            listOf(Receta("1", "nombre", "descripcion", "localizacion", 0.0, 0.0, "urlImagen"))
        val homeRepository = mock<HomeRepositoryImpl> {
            onBlocking { countRecetas() } doReturn 1
            onBlocking { getRecetas() } doReturn recetas
        }
        requestRecetas = RequestRecetas(homeRepository)
        runBlocking { requestRecetas.invoke() }
        verifyBlocking(homeRepository, times(0)) { insertRecetas(recetas) }
        verifyBlocking(homeRepository, times(0)) { getRecetas() }
    }
}