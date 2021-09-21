package com.example.movieapp.ui.adapters



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.models.ResultPopular
import kotlinx.android.synthetic.main.list_popular.view.*

class PopularAdapter(private val items:ArrayList<ResultPopular>): RecyclerView.Adapter<PopularAdapter.MyViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.list_popular,parent,false)
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: PopularAdapter.MyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view) {

        private var tvTitle = view.title

        fun bind(data: ResultPopular){

            tvTitle.text = data.title
        }

    }
}