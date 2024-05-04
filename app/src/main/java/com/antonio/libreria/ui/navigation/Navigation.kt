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
import com.antonio.libreria.ui.screens.ResumenCompras
import com.antonio.libreria.viewmodel.LibreriaViewModel


@Composable
fun Navigation() {
    val navController = rememberNavController() //linea para recordar NavController entre pantallas
    val viewModel= remember{ LibreriaViewModel() } //linea para recordar viewModel entre pantallas

    NavHost(navController, startDestination = Screens.Libreria.route) {
        //pantalla principal con la navegación


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










