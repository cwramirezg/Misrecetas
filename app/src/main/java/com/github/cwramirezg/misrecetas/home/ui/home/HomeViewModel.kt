package com.github.cwramirezg.misrecetas.home.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.cwramirezg.misrecetas.home.domain.home.usecase.HomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCases: HomeUseCases
) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

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
            homeUseCases.getRecetas().collect {
                state = state.copy(
                    recetas = it
                )
            }
        }
    }
}