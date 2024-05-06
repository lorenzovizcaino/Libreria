package com.antonio.libreria.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.antonio.libreria.model.Libro
import java.io.EOFException
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.text.DecimalFormat

class LibreriaViewModel :ViewModel(){


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

    var libroAux by mutableStateOf(Libro(0,"","",0.0,false))
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

    var listaLibrosLeidosFichero = mutableListOf<Libro>()
        private set

    val nombreArchivo="libreria.dat"


    fun setaId(id: Int) {
        this.id=id
    }

    fun setaTitulo(titulo: String){
        this.titulo=titulo
    }

    fun setaAutor(autor: String){
        this.autor=autor
    }

    fun setaPrecio(precio: String){
        var precioInt=precio.toDouble()
        this.precio=precioInt
    }

    fun setaLibro(libro:Libro){
        this.libro=libro
    }

    fun setaLibroAuxiliar(libroAux:Libro){
        this.libroAux=libroAux
    }

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

    fun guardarListaEnFichero(context: Context) {
        var archivo = File(context.filesDir, nombreArchivo)
        if(!archivo.exists()){
            val objectOutputStream = ObjectOutputStream(FileOutputStream(archivo))
            var contador = 0
            listaLibros.forEach { item ->
                contador++

                var libro = Libro(
                    item.id,
                    item.titulo,
                    item.autor,
                    item.precio,
                    item.selecionado
                )

                // Serializar objeto
                serializarObjeto(libro, objectOutputStream)
            }
            objectOutputStream.close()
        }

    }

    fun serializarObjeto(objeto: Libro, objectOutputStream: ObjectOutputStream) {
        objectOutputStream.writeObject(objeto)
    }

    fun leerLibrosArchivo(context: Context): MutableList<Libro> {
        var archivo = File(context.filesDir, nombreArchivo)
        listaLibrosLeidosFichero.clear()
        listaLibrosLeidosFichero = deserializarObjeto(archivo)
        listaLibrosLeidosFichero.sortBy { it.titulo }
        return listaLibrosLeidosFichero
    }

    fun deserializarObjeto(archivo: File): MutableList<Libro> {
        try {
            val objectInputStream = ObjectInputStream(FileInputStream(archivo))

            while (true) {
                try {
                    val libro = objectInputStream.readObject()
                    if (libro is Libro) {
                        listaLibrosLeidosFichero.add(libro)
                        // println(contacto.nombre)
                    } else {
                        break;
                    }

                } catch (ex: EOFException) {
                    break
                }
            }

            objectInputStream.close()
        } catch (ex: IOException) {
            println("Error al leer el archivo: ${ex.message}")
        } catch (ex: ClassNotFoundException) {
            println("Clase no encontrada: ${ex.message}")
        }

        return listaLibrosLeidosFichero
    }

    fun getLibro(libro:Libro){
        this.libro=libro
    }

    fun CalcularId():Int{
        return listaLibrosLeidosFichero.maxOf { it.id }

    }

    fun guardarLibroEnFichero(context: Context,libro: Libro){
        try{
            var archivo = File(context.filesDir, nombreArchivo)

            val objectOutputStream = object : ObjectOutputStream(FileOutputStream(archivo,true)) {
                override fun writeStreamHeader() {}  //para no sobreescribir la cabecera del archivo
            }
            serializarObjeto(libro, objectOutputStream)
            objectOutputStream.close()
            println("Objeto agregado correctamente al archivo.")
        } catch (ex: IOException) {
            println("Error al escribir el objeto en el archivo: ${ex.message}")
        }

    }

    fun borrarLibro(context: Context, libro: Libro) {

        var id = libro.id
        //no se puede borrar con un objeto de una mutablelist mientras se esta recorriendo, por eso se utilizo el removeIf
        //tambien se ha tenido que utilizar la variable id ya que si se comparaba en el removeIf los objetos enteros no funcionaba OK el borrado del objeto
        listaLibrosLeidosFichero.removeIf { it.id == id }
        escribirFichero(context)
    }

    fun escribirFichero(context: Context){
        var archivo = File(context.filesDir, nombreArchivo)
        val objectOutputStream = ObjectOutputStream(FileOutputStream(archivo))
        listaLibrosLeidosFichero.forEach(){item->
            serializarObjeto(item, objectOutputStream)
        }
        objectOutputStream.close()
    }

    fun editarLibroEnFichero(context: Context, libro: Libro, libroAux: Libro) {
        try{
            var archivo = File(context.filesDir, nombreArchivo)
            borrarLibro(context,libro)

            val objectOutputStream = object : ObjectOutputStream(FileOutputStream(archivo,true)) {
                override fun writeStreamHeader() {}  //para no sobreescribir la cabecera del archivo
            }
            serializarObjeto(libroAux, objectOutputStream)
            objectOutputStream.close()
            println("Objeto agregado correctamente al archivo.")
        } catch (ex: IOException) {
            println("Error al escribir el objeto en el archivo: ${ex.message}")
        }


    }

    fun cargarAtributosLibro() {
        setaAutor(libro.autor)
        setaTitulo(libro.titulo)
        setaPrecio(libro.precio.toString())
    }


}