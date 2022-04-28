package br.digitalhouse.loginmvvmdh.model

import android.util.Patterns

data class User (var email : String, var password :String ){

    //valida se o email está vazio
    fun isUserValid () : Boolean{
        return !email.isEmpty()
                && Patterns.EMAIL_ADDRESS.matcher(email).matches()
                && password.length >= 6
    }

}