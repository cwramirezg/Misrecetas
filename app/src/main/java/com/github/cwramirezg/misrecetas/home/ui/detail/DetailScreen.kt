package com.github.cwramirezg.misrecetas.home.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.github.cwramirezg.misrecetas.R
import com.github.cwramirezg.misrecetas.home.domain.model.Receta
import com.github.cwramirezg.misrecetas.home.ui.components.TopAppBarView

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
            topBar = {
                TopAppBarView(
                    textTitle = state.receta.nombre,
                    onClick = { onClickBack() },
                    imageVector = Icons.Default.ArrowBack
                )
            }
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
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(receta.urlImagen)
                .diskCachePolicy(CachePolicy.ENABLED)
                .build(),
            error = painterResource(id = R.drawable.broken_image),
            contentDescription = receta.nombre,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Text(
            text = receta.nombre,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Text(
            text = receta.descripcion,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Button(
            onClick = { onClick(receta.id) },
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.End)
        ) {
            Text(text = "Ver en Google Maps")
        }
    }
}