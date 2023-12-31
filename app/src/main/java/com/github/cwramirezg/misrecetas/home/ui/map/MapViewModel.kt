package com.github.cwramirezg.misrecetas.home.ui.map

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.cwramirezg.misrecetas.core.di.IoDispatcher
import com.github.cwramirezg.misrecetas.home.domain.map.usecase.MapUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val mapUseCases: MapUseCases,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(MapState())
    val state = _state.asStateFlow()

    init {
        val id = savedStateHandle.get<String>("id") ?: ""
        if (id.isNotEmpty()) {
            viewModelScope.launch(dispatcher) {
                val receta = mapUseCases.getRecetaByIdUseCase(id)
                _state.value = _state.value.copy(
                    receta = receta
                )
            }
        }
    }
}