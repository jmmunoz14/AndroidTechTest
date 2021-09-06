package com.example.androidtechtest.DaoTest

import android.content.res.Resources
import com.example.androidtechtest.Models.Teams
import com.example.androidtechtest.R
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("lookup_all_teams.php")
    fun getAllMovies(@Query("id") id: String): Call<Teams>

    companion object {
        var retrofitService: ApiService? = null

        fun getInstance(resources: Resources): ApiService {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(resources.getString(R.string.baseURL))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(ApiService::class.java)
            }
            return retrofitService!!
        }
    }

}