package com.example.newsapp.presentation.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginViewModel(): ViewModel() {
    private val _email = mutableStateOf("")
    val email get() = _email

    private val _password = mutableStateOf("")
    val password get() = _password

    fun setEmail(value: String) {
        _email.value = value
    }

    fun setPass(value: String) {
        _password.value = value
    }
}