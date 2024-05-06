package com.antonio.libreria.ui.navigation

sealed class Screens(val route:String) {

    object Menu: Screens("initial_screen")
    object Libreria: Screens("libreria")
    object LibreriaRoot: Screens("superUsuario libreria")
    object LibreriaAnhadirLibro: Screens("añadir un Libro")
    object ResumenCompras: Screens("Resumen compra Libros")
    object EditarLibro: Screens("Editar Libros Libros")

//    object Agenda: Screens("agenda")
//    object AgendaDetalle: Screens("detalle de agenda")
//    object AgendaAnhadirContacto: Screens("añadir un contacto")
//    object EditarContacto: Screens("editar un contacto")

}