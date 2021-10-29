package com.example.newsapp.presentation.login

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.model.Token
import com.example.newsapp.domain.usecase.LoginUseCase
import com.example.newsapp.utils.NetworkThrowable
import com.example.newsapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val useCase: LoginUseCase): ViewModel() {
    private val _state: MutableState<Resource<Token>> = mutableStateOf(Resource.Init())
    val state: State<Resource<Token>> get() = _state

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

    fun doLogin() {
        _state.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                useCase.invoke(_email.value, _password.value)
            }.onSuccess {
                _state.value = Resource.Success(data = it)
                Log.d("RESULT", it.toString())
            }.onFailure {
                handleError(it)
            }
        }
    }

    private fun handleError(e: Throwable) {
        if (e is NetworkThrowable) {
            _state.value = Resource.Error(
                code = e.errorCode
            )
        } else {
            _state.value = Resource.Error()
        }
    }
}