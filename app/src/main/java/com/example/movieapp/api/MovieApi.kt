package com.example.movieapp.api


import com.example.movieapp.models.NewReleaseResponse
import com.example.movieapp.models.PopularResponse
import com.example.movieapp.models.TrendingResponse
import com.example.movieapp.utils.Utils.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("3/movie/now_playing")
    suspend fun getLatest(
        @Query("apikey") apikey: String = API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") pageNumber: Int = 1
    ):Response<NewReleaseResponse>

    @GET("3/trending/movie/week")
    suspend fun getTrending(
        @Query("apikey") apikey: String = API_KEY
    ):Response<TrendingResponse>

    @GET("3/movie/popular")
    suspend fun getPopular(
        @Query("apikey") apikey: String = API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") pageNumber: Int = 1
    ):Response<PopularResponse>





}