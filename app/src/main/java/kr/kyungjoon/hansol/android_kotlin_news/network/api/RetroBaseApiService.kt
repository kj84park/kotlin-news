package kr.kyungjoon.hansol.android_kotlin_news.network.api

import io.reactivex.Observable
import kr.kyungjoon.hansol.android_kotlin_news.network.dto.GetResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by HANSOL on 2018-03-26.
 */
interface RetroBaseApiService {

    var baseUrl: String
        get() = "https://newsapi.org"
        set(value) = TODO()

    @GET("/v2/top-headlines")
    fun getResponse(@Query("country") country: String, @Query("category") category: String?, @Query("apiKey") apiKey: String): Observable<GetResponse>

}