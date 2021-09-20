package com.example.movieapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.repository.MovieRepository

class HomeViewModelProviderFactory(
    private val movieRepository: MovieRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(movieRepository) as T
    }
}