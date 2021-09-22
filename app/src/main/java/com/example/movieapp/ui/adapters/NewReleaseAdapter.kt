package com.example.movieapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.models.ResultNewRelease
import com.example.movieapp.utils.Utils.Companion.POSTER_BASE_URL
import kotlinx.android.synthetic.main.new_release_list.view.*

class NewReleaseAdapter(private val items:ArrayList<ResultNewRelease>): RecyclerView.Adapter<NewReleaseAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.new_release_list,parent,false)
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view) {

        private val ivTitle = view.iv_title


        fun bind(data: ResultNewRelease){
            val url = POSTER_BASE_URL+data.poster_path
            Glide.with(this.ivTitle).load(url)
                .into(ivTitle)
        }

    }
}