package com.rise.grk.kotlin.ntlg_test.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Url

object RetrofitClient {
    private var retrofit : Retrofit? = null

    fun getClient(baseUrl: String) : Retrofit{

        if(retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }

        return retrofit!!
    }
}

//https://habr.com/ru/post/520544/