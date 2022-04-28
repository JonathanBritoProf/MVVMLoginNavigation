package br.digitalhouse.loginmvvmdh.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.digitalhouse.loginmvvmdh.model.User

class LoginViewModel () : ViewModel(){
    private val user = User("","")

    //cria um mutable Live Data de String
    private val onUserRequestToLogin = MutableLiveData<Boolean>()
    //joga o valor atualizado no liveData para a val
    val onUserRequestLoginLiveData : LiveData<Boolean> = onUserRequestToLogin


    fun onUserRequestLogin(email : String, password : String){
        user.email = email
        user.password = password
        //atualiza o valor do livedata
        onUserRequestToLogin.value = user.isUserValid()
    }

}