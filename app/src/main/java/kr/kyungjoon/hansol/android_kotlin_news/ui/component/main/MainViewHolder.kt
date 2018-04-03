package kr.kyungjoon.hansol.android_kotlin_news.ui.component.main

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.news_item.view.*
import kr.kyungjoon.hansol.android_kotlin_news.network.dto.Articles

class MainViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    fun bind(position: Int, articles: Articles) {

        itemView?.let {
            it.tv_title.text = """[${articles.source.name}]${articles.title}"""

            if (!"Chosun.com".equals(articles.source.name)) {
                it.tv_caption.text = articles.description
            } else {
                it.tv_caption.text = ""
            }
            Glide.with(it).load(articles.urlToImage).into(it.news_image)
        }
    }
}