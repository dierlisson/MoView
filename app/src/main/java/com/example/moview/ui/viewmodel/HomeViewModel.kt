package com.example.moview.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moview.BuildConfig
import com.example.moview.data.RetrofitClient
import com.example.moview.model.Movie
import com.example.moview.model.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchPopularMovies() {
        _isLoading.value = true

        // Usando a API Key do BuildConfig
        val call = RetrofitClient.instance.getPopularMovies(BuildConfig.TMDB_API_KEY)

        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _movies.value = response.body()?.results ?: emptyList()
                } else {
                    _error.value = "Erro na API: ${response.code()}"
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                _isLoading.value = false
                _error.value = "Falha na conexão: ${t.message}"
            }
        })
    }
}