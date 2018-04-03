package kr.kyungjoon.hansol.android_kotlin_news.network.dto

import java.io.Serializable

/**
 * Created by HANSOL on 2018-03-26.
 */
data class GetResponse(
        var articles: List<Articles>,
        var status: String,
        var totalResult: Int
) : Serializable