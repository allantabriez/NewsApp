package com.example.newsapp.domain.repository

interface LoginRepository {
    fun doLogin(username: String, pass: String)
    fun refreshToken()
}