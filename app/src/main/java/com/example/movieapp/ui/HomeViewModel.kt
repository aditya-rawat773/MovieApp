package com.example.movieapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.models.NewReleaseResponse
import com.example.movieapp.models.PopularResponse
import com.example.movieapp.models.TrendingResponse
import com.example.movieapp.repository.MovieRepository
import com.example.movieapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel(
    private val movieRepository: MovieRepository):ViewModel() {

    private val latestMovieData: MutableLiveData<Resource<NewReleaseResponse>> = MutableLiveData()
    private var mNewReleaseMovieResponse: NewReleaseResponse? = null

    private val trendingMovieData: MutableLiveData<Resource<TrendingResponse>> = MutableLiveData()
    private var mTrendingMovieResponse: TrendingResponse? = null

    private val popularMovieData: MutableLiveData<Resource<PopularResponse>> = MutableLiveData()
    private var mPopularMovieResponse: PopularResponse? = null



    fun getLatestPostObserver(): MutableLiveData<Resource<NewReleaseResponse>> {
        return latestMovieData
    }

    fun getTrendingPostObserver(): MutableLiveData<Resource<TrendingResponse>> {
        return trendingMovieData
    }

    fun getPopularPostObserver(): MutableLiveData<Resource<PopularResponse>> {
        return popularMovieData
    }


    fun getLatestMovieData(){
        latestMovieData.postValue(Resource.Loading())
        viewModelScope.launch (Dispatchers.IO) {
            val response = movieRepository.getLatestMovies()
            latestMovieData.postValue(handleLatestResponse(response))
        }
    }

    fun getTrendingMovieData(){
        trendingMovieData.postValue(Resource.Loading())
        viewModelScope.launch (Dispatchers.IO) {
            val response = movieRepository.getTrendingMovies()
            trendingMovieData.postValue(handleTrendingResponse(response))
        }
    }

    fun getPopularMovieData(){
        popularMovieData.postValue(Resource.Loading())
        viewModelScope.launch (Dispatchers.IO) {
            val response = movieRepository.getPopularMovies()
            popularMovieData.postValue(handlePopularResponse(response))
        }
    }

    private fun handleLatestResponse(response: Response<NewReleaseResponse>): Resource<NewReleaseResponse>? {
        if (response.isSuccessful){
            response.body()?.let {
                if(mNewReleaseMovieResponse == null){
                    mNewReleaseMovieResponse = it
                } else {
                    val oldList = mNewReleaseMovieResponse?.results
                    val newList = it.results
                    oldList?.addAll(newList)
                }
                return Resource.Success(mNewReleaseMovieResponse ?: it)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleTrendingResponse(response: Response<TrendingResponse>): Resource<TrendingResponse>? {
        if (response.isSuccessful){
            response.body()?.let {
                if(mTrendingMovieResponse == null){
                    mTrendingMovieResponse = it
                } else {
                    val oldList = mTrendingMovieResponse?.results
                    val newList = it.results
                    oldList?.addAll(newList)
                }
                return Resource.Success(mTrendingMovieResponse ?: it)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handlePopularResponse(response: Response<PopularResponse>): Resource<PopularResponse>? {
        if (response.isSuccessful){
            response.body()?.let {
                if(mPopularMovieResponse == null){
                    mPopularMovieResponse = it
                } else {
                    val oldList = mPopularMovieResponse?.results
                    val newList = it.results
                    oldList?.addAll(newList)
                }
                return Resource.Success(mPopularMovieResponse ?: it)
            }
        }
        return Resource.Error(response.message())
    }
}