package com.antonio.tiendainformatica.ui.miscompose

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun myTextField(number: String, function: (String) -> Unit, operando: String) {
    TextField(
        value = number,
        onValueChange = function,
        label = { Text(operando) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun myTextFieldPass(
    number: String,
    function: (String) -> Unit,
    operando: String,
    visible: Boolean
) {
    /*Esta funcion oculta con asteriscos lo escrito*/
    TextField(
        value = number,
        onValueChange = function,
        label = { Text(operando) },
        visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}