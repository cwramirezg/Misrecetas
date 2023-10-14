package com.github.cwramirezg.misrecetas.home.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.cwramirezg.misrecetas.home.domain.home.usecase.HomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCases: HomeUseCases
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
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = HomeState(loading = true)
            homeUseCases.requestRecetas()
            homeUseCases.getRecetas().collect {
                _state.value = HomeState(recetas = it)
            }
        }
    }
}