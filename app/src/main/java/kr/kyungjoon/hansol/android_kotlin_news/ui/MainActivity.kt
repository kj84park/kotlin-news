package kr.kyungjoon.hansol.android_kotlin_news.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kr.kyungjoon.hansol.android_kotlin_news.R
import kr.kyungjoon.hansol.android_kotlin_news.network.api.RetroBaseApiService
import kr.kyungjoon.hansol.android_kotlin_news.network.dto.Articles
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var retrofit: Retrofit
    lateinit var apiService: RetroBaseApiService
    lateinit var country: String
    lateinit var category: String
    val apikey = "04871c167cfb4a3c9c18b9d170d8ba7f"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        retrofit = Retrofit.Builder().baseUrl("https://newsapi.org/").addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
        apiService = retrofit.create(RetroBaseApiService::class.java)
        country = "kr"
        category = "HeadLine"
        getnews()

        spinner_category.onItemSelectedListener = object : OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                category = parent?.getItemAtPosition(position) as String
                getnews()
            }
        }

        spinner_country.onItemSelectedListener = object : OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                country = parent?.getItemAtPosition(position) as String
                getnews()
            }
        }
    }

    private fun setUpRecyclerView(articles: List<Articles>) {
        recycler_view_news_list.layoutManager = LinearLayoutManager(applicationContext)
        recycler_view_news_list.hasFixedSize()
        recycler_view_news_list.adapter = MainViewAdapter(articles, this)
    }

    private fun getnews() {

        var localCategory : String? = if("Headline" == category){
            null
        } else {
            category
        }

        apiService.getResponse(country, localCategory, apikey).
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                { result ->
                    setUpRecyclerView(result.articles)
                    pb_loading.visibility = View.GONE
                }, { e -> Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show() })
    }
}
