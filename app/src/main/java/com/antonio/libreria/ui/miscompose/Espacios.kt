package com.antonio.tiendainformatica.ui.miscompose

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EspacioH(espacio:Int) {
    Spacer(modifier = Modifier.height(espacio.dp))
}

@Composable
fun EspacioV(espacio:Int) {
    Spacer(modifier = Modifier.width(espacio.dp))
}