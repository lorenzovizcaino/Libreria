package com.antonio.libreria.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.antonio.libreria.model.Libro
import java.text.DecimalFormat

class LibreriaViewModel {


    var listaLibros= mutableListOf<Libro>(
        Libro(1,"EL NIÑO","FERNANDO ARAMBURU",19.47,false),
        Libro(2,"EN AGOSTO NOS VEMOS","GABRIEL GARCIA MARQUEZ",18.90,false),
        Libro(3,"TAN POCA VIDA","HANYA YANAGIHARA",23.65,false),
        Libro(4,"UN ANIMAL SALVAJE","JOEL DICKER",22.70,false),
        Libro(5,"EL HIJO OLVIDADO","MIKEL SANTIAGO",21.75,false),
        Libro(6,"EL ESPEJISMO","HENRIK FEXEUS",22.70,false),
        Libro(7,"LA TEMERARIA","ISABEL SAN SEBASTIAN",21.75,false),
        Libro(8,"NOVIA","ALI HAZELWOOD",18.00,false),
        Libro(9,"BABEL","ALI R. F. KUANG",20.90,false),
        Libro(10,"ALMUDENA","LUIS GARCIA MONTERO",18.90,false),
    )
        private set

    var listaComprar= mutableListOf<Libro>()
        private set

    var libro by mutableStateOf(Libro(0,"","",0.0,false))
        private set

    var id by mutableStateOf(0)
        private set
    var titulo by mutableStateOf("")
        private set
    var autor by mutableStateOf("")
        private set
    var precio by mutableStateOf(0.0)
        private set
    var selecionado by mutableStateOf(false)
        private set

    var sumaProductos by mutableStateOf(0.0)
        private set

    var contadorLibros by mutableStateOf(0)
        private set

    var format= DecimalFormat("#,###.##")
        private set


//    fun setId(id: Int) {
//        this.id=id
//    }
//
//    fun setTitulo(titulo: String){
//        this.titulo=titulo
//    }
//
//    fun setAutor(autor: String){
//        this.autor=autor
//    }
//
//    fun setPrecio(precio: Double){
//        this.precio=precio
//    }

//    fun getListaLibros():MutableList<Libro>{
//        return listaLibros
//    }


    fun sumarProductos(precio: Double) {
        sumaProductos+=precio
    }

    fun restarProductos(precio: Double) {
        sumaProductos-=precio
    }

    fun sumarUnidadeLibros() {
        contadorLibros++
    }

    fun restarUnidadesLibros() {
        contadorLibros--
    }

    fun getlistaComprar():MutableList<Libro>{
        return listaComprar
    }

    fun getTotalFormateado(cantidad:Double): String {
        return format.format(cantidad)
    }


}