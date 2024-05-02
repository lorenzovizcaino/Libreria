package com.antonio.libreria.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
fun Libreria(navController: NavHostController, viewModel: LibreriaViewModel) {
    Scaffold(
        topBar = {
            MyTopBar(navController,viewModel)
        },
        content = {padding ->
            Contenido(navController,viewModel)
        }
    )

}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(navController: NavHostController, viewModel: LibreriaViewModel) {
    val context= LocalContext.current
    TopAppBar(


        title = { Text("Libreria TEIS", fontWeight = FontWeight.Bold, fontSize = 20.sp) },
        actions = {

            IconButton(onClick = {navController.navigate(route = Screens.ResumenCompras.route)}) {
                if(viewModel.contadorLibros>0){
                    BadgedBox(badge = { Badge { Text(text = "${viewModel.contadorLibros}") } }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.AddShoppingCart,
                            contentDescription = "Carrito",
                            tint = Color.White
                        )
                    }
                }else{
                    Icon(
                        imageVector = Icons.Filled.AddShoppingCart,
                        contentDescription = "Carrito",
                        tint = Color.White
                    )
                }

            }





        },


    )
}

@Composable
fun Contenido(navController: NavHostController, viewModel: LibreriaViewModel) {
    val context = LocalContext.current

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(top = 60.dp)
    ) {
        items(viewModel.listaLibros){
            ItemLibros(
                viewModel=viewModel,
                libro=it,

            )
        }
    }
}

@Composable
fun ItemLibros(viewModel: LibreriaViewModel, libro: Libro) {
    var isChecked by remember { mutableStateOf(libro.selecionado) }
    Card(border = BorderStroke(2.dp, Color.DarkGray),modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp, vertical = 8.dp)) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(Color.Yellow)){
            Column(modifier=Modifier.padding(10.dp)){
                Text(text = libro.titulo)
                Text(text = libro.autor)
                
            }
            Column(horizontalAlignment = Alignment.End, modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)){
                Text(text = libro.precio.toString())
                Checkbox(checked = isChecked, onCheckedChange = {
                    isChecked=it
                    libro.selecionado=isChecked
                    if (isChecked) {
                        viewModel.sumarProductos(libro.precio)
                        viewModel.sumarUnidadeLibros()
                        viewModel.listaComprar.add(libro)

                    } else {
                        viewModel.restarProductos(libro.precio)
                        viewModel.restarUnidadesLibros()
                        viewModel.listaComprar.remove(libro)
                    }

                })

            }
        }
        
    }
}
