package com.example.remote.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit

open
class GithubTrendingServiceFactory {

    fun makeGithubTrandingService(isDebug: Boolean):GithubTrendingService{
        val client = makeOkHttpClient(makeLogginInterceptor(isDebug))
        return makeGithubTrendingService(client)
    }
    private fun makeGithubTrendingService(okHttpClient: OkHttpClient):GithubTrendingService{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return retrofit.create(GithubTrendingService::class.java)
    }

    private fun makeOkHttpClient(httpLogginInterceptor:HttpLoggingInterceptor) : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLogginInterceptor)
            .connectTimeout(120,TimeUnit.SECONDS)
            .readTimeout(120,TimeUnit.SECONDS)
            .build()
    }

    private fun makeLogginInterceptor(isDebug: Boolean) :HttpLoggingInterceptor {
        val loggin = HttpLoggingInterceptor()
        loggin.level = if(isDebug){
            HttpLoggingInterceptor.Level.BODY
        }else{
            HttpLoggingInterceptor.Level.NONE
        }
        return loggin
    }
}