package com.example.movieapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.models.ResultTrending
import com.example.movieapp.utils.Utils
import kotlinx.android.synthetic.main.list_trending.view.*

class TrendingAdapter(private var items: ArrayList<ResultTrending>): RecyclerView.Adapter<TrendingAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrendingAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.list_trending,parent,false)
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: TrendingAdapter.MyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view) {

        private var ivTitle = view.iv_title

        fun bind(data: ResultTrending){

            val url = Utils.POSTER_BASE_URL +data.poster_path
            Glide.with(ivTitle).load(url).into(ivTitle)
        }

    }
}
