package com.example.movieapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.models.ResultNewRelease
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

        private var tvTitle = view.title

        fun bind(data: ResultNewRelease){

            tvTitle.text = data.title
        }

    }
}