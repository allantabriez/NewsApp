package com.example.newsapp.presentation.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.model.News
import com.example.newsapp.domain.model.Profile
import com.example.newsapp.domain.usecase.GetNewsUseCase
import com.example.newsapp.domain.usecase.GetProfileUseCase
import com.example.newsapp.utils.DataMapper
import com.example.newsapp.utils.Resource
import kotlinx.coroutines.launch

class HomeViewModel(
    private val newsUseCase: GetNewsUseCase,
    private val profileUseCase: GetProfileUseCase,
) : ViewModel() {

    private val _state: MutableState<Resource<Pair<List<News>, Profile>>> = mutableStateOf(Resource.Init())
    val state: State<Resource<Pair<List<News>, Profile>>> get() = _state

    init {
        getData()
//        getProfile()
    }

//    fun getNews() {
//        viewModelScope.launch {
//            runCatching {
//                newsUseCase.invoke()
//            }.onSuccess {
//                Log.d("response", it.toString())
//            }.onFailure {
//                DataMapper.handleError<List<News>>(it)
//            }
//        }
//    }
//
    fun getProfile() {
        viewModelScope.launch {
            runCatching {
                profileUseCase.invoke()
            }.onSuccess {
                _state.value = Resource.Success(Pair(first = emptyList(), second = it))
            }.onFailure {
                DataMapper.handleError<Profile>(it)
            }
        }
    }

    private fun getData() {
        viewModelScope.launch {
//            runCatching {
//                val news = async {
//                    newsUseCase.invoke()
//                }
//                val profile = async {
//                    profileUseCase.invoke()
//                }
//                Pair(first = news.await(), second = profile.await())
//            }.onSuccess {
//                _state.value = Resource.Success(data = it)
//            }.onFailure {
//                _state.value = DataMapper.handleError(it)
//            }

            runCatching {
                val newsResult = runCatching {
                    newsUseCase.invoke()
                }
                val profileResult = runCatching {
                    profileUseCase.invoke()
                }
                Pair(first = newsResult.getOrThrow(), second = profileResult.getOrThrow())
            }.onSuccess {
                _state.value = Resource.Success(data = it)
            }.onFailure {
                _state.value = DataMapper.handleError(it)
            }
        }
    }
}