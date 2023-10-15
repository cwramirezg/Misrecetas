package com.github.cwramirezg.misrecetas.home.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.github.cwramirezg.misrecetas.R
import com.github.cwramirezg.misrecetas.home.domain.model.Receta
import com.github.cwramirezg.misrecetas.home.ui.components.SearchView
import com.github.cwramirezg.misrecetas.home.ui.components.TopAppBarView

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onClickReceta: (String) -> Unit
) {
    val state by viewModel.state.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                TopAppBarView(
                    textTitle = "Mis Recetas",
                    onClick = { },
                    imageVector = Icons.Default.Home
                )
            }
        ) { padding ->
            if (state.loading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            if (state.recetas.isNotEmpty()) {
                val textState = remember { mutableStateOf(TextFieldValue("")) }
                Column(modifier = Modifier.padding(padding)) {
                    SearchView(state = textState)
                    val searchedText = textState.value.text
                    val filter = if (searchedText.isEmpty()) {
                        state.recetas
                    } else {
                        state.recetas.filter {
                            it.nombre.contains(searchedText, ignoreCase = true)
                        }
                    }
                    if (filter.isNotEmpty()) {
                        LazyVerticalGrid(
                            columns = GridCells.Adaptive(180.dp),
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                            contentPadding = PaddingValues(4.dp)
                        ) {
                            items(filter) { receta ->
                                RecetaItem(
                                    receta = receta,
                                    onClick = {
                                        onClickReceta(it)
                                    }
                                )
                            }
                        }
                    } else {
                        Text(
                            text = "No se encontraron recetas",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RecetaItem(receta: Receta, onClick: (String) -> Unit) {
    Column(
        modifier = Modifier.clickable {
            onClick(receta.id)
        }
    ) {
        Box {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(receta.urlImagen)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .build(),
                error = painterResource(id = R.drawable.broken_image),
                contentDescription = receta.nombre,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(1.0f),
            )
        }
        Text(
            text = receta.nombre,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
    }
}