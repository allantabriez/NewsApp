package com.example.newsapp.presentation.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.model.Token
import com.example.newsapp.domain.usecase.LoginUseCase
import com.example.newsapp.utils.DataMapper
import com.example.newsapp.utils.EspressoIdlingResource
import com.example.newsapp.utils.Resource
import kotlinx.coroutines.launch

class LoginViewModel(private val useCase: LoginUseCase): ViewModel() {
    private val _state: MutableState<Resource<Token>> = mutableStateOf(Resource.Init())
    val state: State<Resource<Token>> get() = _state

    private val _email = mutableStateOf("")
    val email get() = _email

    private val _password = mutableStateOf("")
    val password get() = _password

    private val _emailError = mutableStateOf(false)
    val emailError get() = _emailError

    private val _passError = mutableStateOf(false)
    val passError get() = _passError

    fun setEmail(value: String) {
        _email.value = value
    }

    fun setPass(value: String) {
        _password.value = value
    }

    fun resetState() {
        _state.value = Resource.Init()
    }

    fun doLogin() {
        EspressoIdlingResource.increment()
        _state.value = Resource.Loading()
        viewModelScope.launch {
            runCatching {
                useCase.invoke(_email.value, _password.value)
            }.onSuccess {
                _state.value = Resource.Success(data = it)
                EspressoIdlingResource.decrement()
            }.onFailure {
                _state.value = DataMapper.handleError(it)
                EspressoIdlingResource.decrement()
            }
        }
    }

    fun areInputsValid(): Boolean {
        if (!isEmailValid()) return false
        if (!isPassValid()) return false

        return true
    }

    private fun isEmailValid(): Boolean {
        return if (_email.value.isBlank()) {
            _emailError.value = true
            false
        } else {
            _emailError.value = false
            true
        }
    }

    private fun isPassValid(): Boolean {
        return if (_password.value.isBlank()) {
            _passError.value = true
            false
        } else {
            _passError.value = false
            true
        }
    }

}