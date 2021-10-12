package com.example.newsapp.data.remote.network

import com.example.newsapp.data.remote.response.DataResponse
import com.example.newsapp.data.remote.response.NewsResponse
import com.example.newsapp.data.remote.response.ProfileResponse
import com.example.newsapp.utils.Constants.NEWS_URL
import com.example.newsapp.utils.Constants.PROFILE_URL
import retrofit2.Response
import retrofit2.http.GET

interface MeService {

    @GET(PROFILE_URL)
    suspend fun getProfile(): Response<ProfileResponse>

    @GET(NEWS_URL)
    suspend fun getNews(): Response<DataResponse<List<NewsResponse>>>
}