package com.github.cwramirezg.misrecetas.home.ui.home

import com.github.cwramirezg.misrecetas.home.domain.home.usecase.GetRecetasUseCase
import com.github.cwramirezg.misrecetas.home.domain.home.usecase.HomeUseCases
import com.github.cwramirezg.misrecetas.home.domain.home.usecase.RequestRecetas
import com.github.cwramirezg.misrecetas.home.domain.model.Receta
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyBlocking

class HomeViewModelTest {

    private lateinit var homeViewModel: HomeViewModel

    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Si hay recetas, devuelve en el estado inicial`() = scope.runTest {
        val recetas =
            listOf(Receta("1", "nombre", "descripcion", "localizacion", 0.0, 0.0, "urlImagen"))
        val requestRecetas = mock<RequestRecetas> {
            onBlocking { invoke() } doReturn Unit
        }
        val getRecetas = mock<GetRecetasUseCase> {
            onBlocking { invoke() } doReturn flowOf(recetas)
        }
        val homeUseCases = HomeUseCases(requestRecetas, getRecetas)
        homeViewModel = HomeViewModel(
            homeUseCases = homeUseCases,
            dispatcher = dispatcher
        )
        advanceUntilIdle()
        verifyBlocking(requestRecetas) { invoke() }
        verifyBlocking(getRecetas) { invoke() }
        val state = homeViewModel.state.value
        assertEquals(HomeState(recetas = recetas), state)
    }
}