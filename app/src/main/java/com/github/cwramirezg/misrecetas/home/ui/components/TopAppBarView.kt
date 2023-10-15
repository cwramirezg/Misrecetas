package com.github.cwramirezg.misrecetas.home.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarView(
    textTitle: String,
    onClick: () -> Unit,
    imageVector: ImageVector
) {
    CenterAlignedTopAppBar(
        title = { Text(text = textTitle) },
        navigationIcon = {
            IconButton(onClick = { onClick() }) {
                Icon(
                    imageVector = imageVector,
                    contentDescription = "Icon navigation"
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun TopAppBarViewPreview() {
    TopAppBarView(
        textTitle = "Mis Recetas",
        onClick = {},
        imageVector = Icons.Default.Home
    )
}