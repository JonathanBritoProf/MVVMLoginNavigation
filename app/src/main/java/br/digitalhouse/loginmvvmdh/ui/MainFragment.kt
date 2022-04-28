package br.digitalhouse.loginmvvmdh.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.digitalhouse.loginmvvmdh.Interfaces.LoginResultInterface
import br.digitalhouse.loginmvvmdh.R
import br.digitalhouse.loginmvvmdh.viewModel.LoginViewModel


class MainFragment : Fragment(R.layout.fragment_main), LoginResultInterface {

    //cria o objeto viewModel
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //configura o view model
        loginViewModel = ViewModelProvider(this).get(LoginViewModel ::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //configura o ouvidor de eventos
        setupListeners(view)
        //configura o observador de atualização de dados
        setupObservers()
    }

    private fun setupListeners(view: View) {
        var btnLogin = view.findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            var email = view.findViewById<EditText>(R.id.txtEmail).text.toString()
            var password = view.findViewById<EditText>(R.id.txtPassword).text.toString()
            //chama a função para atualizar o valor do view model
            loginViewModel.onUserRequestLogin(email,password)
        }
    }

    private fun setupObservers(){
        //observa a alteração no valor da variavel e caso haja alteração chama a função de mostrar mensagem
        loginViewModel.onUserRequestLoginLiveData.observe(this,{
            if(it){
                changeScreen()
                showMessage("Login Sucess")
            }else
                showMessage("LoginError")
        }
        )
    }

    private fun changeScreen(){
        findNavController().navigate(R.id.action_mainFragment_to_homeActivity)
    }

    override fun showMessage(message: String) {
        Toast.makeText(requireContext(),message, Toast.LENGTH_LONG).show()
    }

}