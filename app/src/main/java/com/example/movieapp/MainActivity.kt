package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.repository.MovieRepository
import com.example.movieapp.ui.HomeViewModel
import com.example.movieapp.ui.HomeViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movieRepository = MovieRepository()
        val viewModelProviderFactory = HomeViewModelProviderFactory(movieRepository)
        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(HomeViewModel::class.java)
    }
}