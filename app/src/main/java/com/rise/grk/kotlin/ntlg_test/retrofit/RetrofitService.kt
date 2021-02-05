package com.rise.grk.kotlin.ntlg_test.retrofit

import com.rise.grk.kotlin.ntlg_test.CourseInfo
import com.rise.grk.kotlin.ntlg_test.Data
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {
    @GET("/netology-code/rn-task/master/netology.json")
    suspend fun getdata() : Response<Data>
}