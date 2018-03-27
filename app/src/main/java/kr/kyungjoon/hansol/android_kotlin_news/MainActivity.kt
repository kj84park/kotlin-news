package kr.kyungjoon.hansol.android_kotlin_news

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kr.kyungjoon.hansol.android_kotlin_news.network.api.RetroBaseApiService
import kr.kyungjoon.hansol.android_kotlin_news.network.dto.Articles
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var retrofit : Retrofit
    lateinit var apiService: RetroBaseApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var apikey  = "04871c167cfb4a3c9c18b9d170d8ba7f";
        setContentView(R.layout.activity_main)

        retrofit = Retrofit.Builder().baseUrl("https://newsapi.org/").
                addConverterFactory(GsonConverterFactory.create()).
                addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
        apiService = retrofit.create(RetroBaseApiService::class.java)
        getResponse("kr","Business",apikey)

    }

    private fun setUpRecyclerView(articles: List<Articles>){
//        recycler_view_news_list.layoutManager = LinearLayoutManager(this)
//        recycler_view_news_list.hasFixedSize()
//        recycler_view_news_list.adapter()
        Toast.makeText(this,articles.get(0).title,Toast.LENGTH_SHORT).show()
    }

    private fun getResponse(
            country : String,
            category : String,
            apiKey : String
    ){
        apiService.getResponse(country, category, apiKey).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                { result -> setUpRecyclerView(result.articles) },
                { e -> Toast.makeText(this,e.message,Toast.LENGTH_SHORT).show()})
    }
}
