package com.antonio.libreria.model

import java.io.Serializable

data class Libro(
    var id:Int,
    var titulo:String,
    var autor:String,
    var precio:Double,
    var selecionado:Boolean
):Serializable
