package com.antonio.libreria.viewmodel

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.antonio.libreria.model.Login

class InicioViewModel : ViewModel(){
    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var loginenabled by mutableStateOf(false)
        private set

    var usuarioCorrecto by mutableStateOf(false)
        private set

    var listaLogin= mutableListOf<Login>(
        Login("lorenzovizcaino@gmail.com","123QWEasd"),
        Login("juanvaldes@gmail.com","juanvaldes"),
        Login("anacamino@yahoo.es","anacamino")

    )



    fun getEmail(email: String) {
        this.email = email
    }

    fun getPassword(password: String) {
        this.password = password
    }

    fun getloginenabled(loginenabled: Boolean) {
        this.loginenabled = Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6
    }

    fun getusuarioCorrecto(usuarioCorrecto: Boolean){
        this.usuarioCorrecto=usuarioCorrecto;
    }
}