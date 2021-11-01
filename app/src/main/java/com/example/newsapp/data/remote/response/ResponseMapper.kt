package com.example.newsapp.data.remote.response

interface ResponseMapper<Model, Entity> {
    fun toModel(): Model
    fun toEntity(): Entity
}