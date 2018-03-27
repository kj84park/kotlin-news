package kr.kyungjoon.hansol.android_kotlin_news.network.dto

/**
 * Created by HANSOL on 2018-03-26.
 */
data class Articles (
   var author : String,
   var title : String,
    var description : String,
    var url : String,
    var urlToImage : String,
    var publishedAt: String,
    var source : Source
)