package kr.kyungjoon.hansol.android_kotlin_news.ui.component.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import kr.kyungjoon.hansol.android_kotlin_news.R
import kr.kyungjoon.hansol.android_kotlin_news.network.dto.Articles
import kr.kyungjoon.hansol.android_kotlin_news.ui.listener.RecyclerItemListener

class MainViewAdapter(private val news: List<Articles>, private val context: Context, private val recyclerItemListener: RecyclerItemListener) : RecyclerView.Adapter<MainViewHolder>() {

    override fun getItemCount(): Int {
        return news.size
    }

    override fun onBindViewHolder(holder: MainViewHolder?, position: Int) {

        holder?.itemView?.setOnClickListener {
            recyclerItemListener.onItemSelected(position)
        }
        holder?.bind(position, news[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(context).inflate(R.layout.news_item, parent, false))
    }

}