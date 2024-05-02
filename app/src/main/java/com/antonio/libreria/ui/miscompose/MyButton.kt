package com.antonio.tiendainformatica.ui.miscompose

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyButton(function: () -> Unit,
             texto: String,
             color: Color = Color.Blue,
             forma: Shape = RectangleShape,
             elevacion: Dp =0.dp,
             colorBorde: Color = Color.Blue,
             grosorBorde: Dp =0.dp,
             colorTexto: Color=Color.Black,
             tamanhoTexto: TextUnit=15.sp) {
    Button(
        onClick = function,
        colors = ButtonDefaults.buttonColors(color),
        shape = forma,
        elevation = ButtonDefaults.buttonElevation(elevacion),
        border = BorderStroke(grosorBorde, colorBorde),
    )


    {
        Text(text = texto, color = colorTexto, fontSize = tamanhoTexto)

    }
}