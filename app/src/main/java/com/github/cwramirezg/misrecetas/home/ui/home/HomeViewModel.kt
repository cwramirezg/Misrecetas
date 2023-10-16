package com.github.cwramirezg.misrecetas.home.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.cwramirezg.misrecetas.core.di.IoDispatcher
import com.github.cwramirezg.misrecetas.home.domain.home.usecase.HomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCases: HomeUseCases,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        getRecetas()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnClickReceta -> {

            }
        }
    }

    private fun getRecetas() {
        viewModelScope.launch(dispatcher) {
            _state.value = HomeState(loading = true)
            homeUseCases.requestRecetas()
            homeUseCases.getRecetas().collect {
                _state.value = HomeState(recetas = it)
            }
        }
    }
}