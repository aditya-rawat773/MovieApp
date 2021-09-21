package com.example.movieapp.ui

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.models.ResultNewRelease
import com.example.movieapp.models.ResultPopular
import com.example.movieapp.models.ResultTrending
import com.example.movieapp.repository.MovieRepository
import com.example.movieapp.ui.adapters.NewReleaseAdapter
import com.example.movieapp.ui.adapters.PopularAdapter
import com.example.movieapp.ui.adapters.TrendingAdapter
import com.example.movieapp.utils.Resource
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    lateinit var viewModel: HomeViewModel
    private lateinit var homeViewModel: HomeViewModel

    private lateinit var mNewReleaseAdapter: NewReleaseAdapter
    private lateinit var mPopularAdapter: PopularAdapter
    private lateinit var mTrendingAdapter: TrendingAdapter

    private var listNewRelease = ArrayList<ResultNewRelease>()
    private var listPopular = ArrayList<ResultPopular>()
    private var listTrending = ArrayList<ResultTrending>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieRepository = MovieRepository()
        val viewModelProviderFactory = HomeViewModelProviderFactory(movieRepository)

        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(HomeViewModel::class.java)

        homeViewModel = viewModel


        rv_new_release.apply {
            mNewReleaseAdapter = NewReleaseAdapter(listNewRelease)
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            adapter = mNewReleaseAdapter
        }

        rv_trending.apply {
            mTrendingAdapter = TrendingAdapter(listTrending)
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            adapter = mTrendingAdapter
        }

        rv_popular.apply {
            mPopularAdapter = PopularAdapter(listPopular)
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            adapter = mPopularAdapter
        }



        newRelease()
        trending()
        popular()

    }

    private fun newRelease(){
        homeViewModel.getNewReleaseMovieData()

        homeViewModel.getNewReleaseMoviePostObserver().observe(viewLifecycleOwner,{

            when(it){
                is Resource.Success ->{
                    // hideProgressBar()
                    it.data?.let { response ->
                        listNewRelease.addAll(response.results)
                        Log.d("adi", "onViewCreated: $listNewRelease")
                        mNewReleaseAdapter.notifyDataSetChanged()

                    }
                }
                is Resource.Error ->{
                    // hideProgressBar()
                    it.message?.let{ message ->

                        Log.e(ContentValues.TAG,"An error occur $message")
                    }
                }
                is Resource.Loading ->{
                    //showProgressBar()
                }
            }
        })
    }

    private fun trending(){
        homeViewModel.getTrendingMovieData()
        homeViewModel.getTrendingPostObserver().observe(viewLifecycleOwner,{

            when(it){
                is Resource.Success ->{
                    // hideProgressBar()
                    it.data?.let { response ->
                        listTrending.addAll(response.results)
                        Log.d("aditya", "onViewCreated: $listTrending")
                        mNewReleaseAdapter.notifyDataSetChanged()

                    }
                }
                is Resource.Error ->{
                    // hideProgressBar()
                    it.message?.let{ message ->

                        Log.e(ContentValues.TAG,"An error occur $message")
                    }
                }
                is Resource.Loading ->{
                    //showProgressBar()
                }
            }
        })
    }

    private fun popular(){
        homeViewModel.getPopularMovieData()

        homeViewModel.getPopularPostObserver().observe(viewLifecycleOwner,{

            when(it){
                is Resource.Success ->{
                    // hideProgressBar()
                    it.data?.let { response ->
                        listPopular.addAll(response.results)
                        Log.d("adi", "onViewCreated: $listPopular")
                        mNewReleaseAdapter.notifyDataSetChanged()

                    }
                }
                is Resource.Error ->{
                    // hideProgressBar()
                    it.message?.let{ message ->

                        Log.e(ContentValues.TAG,"An error occur $message")
                    }
                }
                is Resource.Loading ->{
                    //showProgressBar()
                }
            }
        })
    }
}