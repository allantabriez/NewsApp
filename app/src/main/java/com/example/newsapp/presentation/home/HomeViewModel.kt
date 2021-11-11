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
import com.example.newsapp.utils.EspressoIdlingResource
import com.example.newsapp.utils.Resource
import kotlinx.coroutines.launch

class HomeViewModel(
    private val newsUseCase: GetNewsUseCase,
    private val profileUseCase: GetProfileUseCase,
) : ViewModel() {

    private val _state: MutableState<Resource<Pair<List<News>, Profile>>> =
        mutableStateOf(Resource.Init())
    val state: State<Resource<Pair<List<News>, Profile>>> get() = _state

    init {
        getData()
    }

    private fun getData() {
        EspressoIdlingResource.increment()
        _state.value = Resource.Loading()
        viewModelScope.launch {
            runCatching {
                val newsResult = newsUseCase.invoke()
                val profileResult = profileUseCase.invoke()
                newsResult to profileResult
            }.onSuccess {
                _state.value = Resource.Success(data = it)
                EspressoIdlingResource.decrement()
            }.onFailure {
                _state.value = DataMapper.handleError(it)
                EspressoIdlingResource.decrement()
            }
        }
    }
}