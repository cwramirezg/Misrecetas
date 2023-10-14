package com.github.cwramirezg.misrecetas.home.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.cwramirezg.misrecetas.home.domain.home.usecase.HomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCases: HomeUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    private var currentDayJob: Job? = null

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
        currentDayJob?.cancel()
        currentDayJob = viewModelScope.launch {
            _state.value = HomeState(loading = true)
            homeUseCases.getRecetas().collectLatest {
                _state.value = _state.value.copy(
                    loading = false,
                    recetas = it
                )
            }
        }
    }
}