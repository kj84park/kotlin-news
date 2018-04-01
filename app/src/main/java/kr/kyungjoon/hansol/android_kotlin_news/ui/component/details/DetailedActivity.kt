package kr.kyungjoon.hansol.android_kotlin_news.ui.component.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_detailed.*
import kr.kyungjoon.hansol.android_kotlin_news.R
import kr.kyungjoon.hansol.android_kotlin_news.network.dto.Articles

class DetailedActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        val intent = intent
        val article = intent.getSerializableExtra("article") as Articles

        news_web_view.webViewClient = WebViewClient()
        news_web_view.settings.javaScriptEnabled = true
        news_web_view.settings.builtInZoomControls = true
        news_web_view.loadUrl(article.url)
    }
}
