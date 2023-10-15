package com.github.cwramirezg.misrecetas.home.ui.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.cwramirezg.misrecetas.home.ui.components.TopAppBarView
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    viewModel: MapViewModel = hiltViewModel(),
    onClickBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                TopAppBarView(
                    textTitle = "${state.receta.nombre}(${state.receta.localizacion})",
                    onClick = { onClickBack() },
                    imageVector = Icons.Default.ArrowBack
                )
            }
        ) { padding ->
            if (state.receta.lat != 0.0 && state.receta.lon != 0.0) {
                val location = LatLng(state.receta.lat, state.receta.lon)
                val cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(location, 5f)
                }
                GoogleMap(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize(),
                    cameraPositionState = cameraPositionState
                ) {
                    Marker(
                        state = MarkerState(position = location),
                        title = state.receta.localizacion
                    )
                }
            } else {
                Text(
                    text = "No se encontro la localización de la receta",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize(),
                )
            }
        }
    }
}