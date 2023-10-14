package com.github.cwramirezg.misrecetas.home.ui.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.github.cwramirezg.misrecetas.home.domain.model.Receta

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    onClickBack: () -> Unit,
    onClickMap: (String) -> Unit
) {
    val state by viewModel.state.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = { TopAppBar(title = { Text(text = "Detalle de la receta") }) }
        ) { padding ->
            if (state.receta.id.isNotEmpty()) {
                MyReceta(
                    receta = state.receta,
                    modifier = Modifier.padding(padding),
                    onClick = { onClickMap(it) }
                )
            }
        }
    }
}

@Composable
fun MyReceta(
    receta: Receta,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit
) {
    Column(
        modifier = modifier.clickable { onClick(receta.id) }
    ) {
        Box {
            AsyncImage(
                model = receta.urlImagen,
                contentDescription = receta.nombre,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(2 / 3f)
            )
        }
        Text(text = receta.nombre)
    }
}