package com.antonio.libreria.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.antonio.libreria.ui.screens.EditarLibro

import com.antonio.libreria.ui.screens.Libreria
import com.antonio.libreria.ui.screens.LibreriaAnhadirLibro
import com.antonio.libreria.ui.screens.LibreriaRoot
import com.antonio.libreria.ui.screens.Menu
import com.antonio.libreria.ui.screens.ResumenCompras
import com.antonio.libreria.viewmodel.InicioViewModel
import com.antonio.libreria.viewmodel.LibreriaViewModel


@Composable
fun Navigation() {
    val navController = rememberNavController() //linea para recordar NavController entre pantallas
    val viewModel= remember{ LibreriaViewModel() } //linea para recordar viewModel entre pantallas
    val viewModelInicio= remember{ InicioViewModel() } //linea para recordar viewModel entre pantallas

    NavHost(navController, startDestination = Screens.Menu.route) {
        //pantalla principal con la navegación
        composable(route = Screens.Menu.route) {
            Menu(navController,viewModelInicio,viewModel) //Nombre de la función composable a la que navegar
        }
        composable(route = Screens.LibreriaRoot.route) {
            LibreriaRoot(navController,viewModel) //Nombre de la función composable a la que navegar
        }

        composable(route = Screens.Libreria.route) {
            Libreria(navController,viewModel) //Nombre de la función composable a la que navegar
        }
        composable(route = Screens.LibreriaAnhadirLibro.route) {
            LibreriaAnhadirLibro(navController,viewModel) //Nombre de la función composable a la que navegar
        }
        composable(route = Screens.ResumenCompras.route) {
            ResumenCompras(navController,viewModel) //Nombre de la función composable a la que navegar
        }
        composable(route = Screens.EditarLibro.route) {
            EditarLibro(navController,viewModel) //Nombre de la función composable a la que navegar
        }





    }
}










