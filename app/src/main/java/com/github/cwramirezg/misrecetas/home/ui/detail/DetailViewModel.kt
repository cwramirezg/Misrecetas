package com.github.cwramirezg.misrecetas.home.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.cwramirezg.misrecetas.home.domain.detail.usecase.DetailUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val detailUseCases: DetailUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state = _state.asStateFlow()

    init {
        val id = savedStateHandle.get<String>("id") ?: ""
        Timber.d("ID: $id")
        if (id.isNotEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                val receta = detailUseCases.getRecetaByIdUseCase(id)
                _state.value = _state.value.copy(
                    receta = receta
                )
            }
        }
    }
}