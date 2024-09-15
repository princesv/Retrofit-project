package com.example.retrofitproject2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitproject2.R
import com.example.retrofitproject2.pojo.Article
import com.example.retrofitproject2.pojo.NewsArticles
import com.example.retrofitproject2.room.ArticlesViewModel

class NewsAdapter(val vmNewsArticles: ArticlesViewModel): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    lateinit var mListener:onNewsItemClickListener
    inner class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tv=itemView.findViewById<TextView>(R.id.news_title)
        init {
            itemView.setOnClickListener(View.OnClickListener {
                mListener.onItemClickListener(absoluteAdapterPosition)
            })
        }
    }

    interface onNewsItemClickListener{
        fun onItemClickListener(position: Int)
    }
    fun callFromMainActivity(onNewsItemClickListener: onNewsItemClickListener){
        mListener=onNewsItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return NewsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return vmNewsArticles.getNewsArticleList().size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.tv.setText(vmNewsArticles.getNewsArticleList().get(position).title)
    }
}