package kr.kyungjoon.hansol.android_kotlin_news.network.listener

/**
 * Created by HANSOL on 2018-03-27.
 */
interface newsApiCallback<T>{
    fun onSuccess(receiveData : T)
    fun onError(t : Throwable)
    fun onFailure(code : Int)
}