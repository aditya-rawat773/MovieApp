package com.example.movieapp.repository

import com.example.movieapp.api.RetrofitInstance
import com.example.movieapp.models.NewReleaseResponse
import com.example.movieapp.models.PopularResponse
import com.example.movieapp.models.TrendingResponse
import retrofit2.Response

class MovieRepository {

    suspend fun getPopularMovies(): Response<PopularResponse> {
        return RetrofitInstance.api.getPopular()
    }

    suspend fun getTrendingMovies(): Response<TrendingResponse> {
        return RetrofitInstance.api.getTrending()
    }

    suspend fun getNewReleaseMovies(): Response<NewReleaseResponse> {
        return RetrofitInstance.api.getNewRelease()
    }

}