package com.antonio.libreria.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.antonio.libreria.model.Libro
import com.antonio.libreria.ui.navigation.Screens
import com.antonio.libreria.viewmodel.LibreriaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibreriaAnhadirLibro(navController: NavHostController, viewModel: LibreriaViewModel) {
    Scaffold(
        topBar = {
            MyTopBar3(navController, viewModel)
        },
        content = { padding ->
            ContenidoDetalleAnhadir(navController, viewModel)
        },


        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContenidoDetalleAnhadir(navController: NavHostController, viewModel: LibreriaViewModel) {

    Card(
        border = BorderStroke(2.dp, Color.DarkGray), modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 80.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                TextField(
                    value = viewModel.titulo,
                    onValueChange = { viewModel.setaTitulo(it) },
                    label = {
                        Text(text = "Titulo")
                    })
                Spacer(modifier = Modifier.size(2.dp))
                TextField(
                    value = viewModel.autor,
                    onValueChange = { viewModel.setaAutor(it) },
                    label = {
                        Text(text = "Autor")
                    })


            }
            Column(
                horizontalAlignment = Alignment.End, modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {

                TextField(
                    value = (viewModel.precio).toString(),
                    onValueChange = { viewModel.setaPrecio(it) },
                    label = {
                        Text(text = "Precio")
                    })


            }
        }

    }
}

@Composable
fun MyTopBar3(navController: NavHostController, viewModel: LibreriaViewModel) {
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    TopAppBar(

        navigationIcon = {
            IconButton(onClick = { navController.navigate(route = Screens.LibreriaRoot.route) }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Ir hacia atras",

                    modifier = Modifier.size(60.dp)
                )
            }


        },
        title = {
            Text(
                "Libreria",

                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        },
        actions = {

            IconButton(onClick = {
                showDialog = true

            }) {
                Icon(
                    imageVector = Icons.Filled.Save,
                    contentDescription = "guardar contacto",

                    modifier = Modifier.size(70.dp)
                )
            }


        })
    if (showDialog) {
        MyDialogGuardarLibro(
            onDismiss = { showDialog = false },
            onAccept = {


                viewModel.getLibro(
                    Libro(
                        viewModel.CalcularId() + 1,
                        viewModel.titulo,
                        viewModel.autor,
                        viewModel.precio,
                        viewModel.selecionado
                    )
                )
                viewModel.guardarLibroEnFichero(context, viewModel.libro)
                showDialog = false
                navController.navigate(route = Screens.LibreriaRoot.route)


            }
        )
    }
}

@Composable
fun MyDialogGuardarLibro(onDismiss: () -> Unit, onAccept: () -> Unit) {

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Guardar Contacto")
        },
        text = {
            Text(text = "¿Estás seguro de que deseas guardar este contacto?")
        },
        confirmButton = {
            Button(
                onClick = {
                    onAccept()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                shape = RectangleShape
            ) {
                Text("Aceptar")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    onDismiss()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                shape = RectangleShape
            ) {
                Text("Cancelar")
            }
        }
    )
}
