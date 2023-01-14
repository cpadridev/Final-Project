package com.cpadridev.carmonaadrian_finalproject.connection

import com.cpadridev.carmonaadrian_finalproject.model.Word
import retrofit2.Call
import retrofit2.http.*

interface Api {
    @GET
    fun getAll(@Url endpoint: String): Call<ArrayList<Word>>

    @GET("numbers")
    fun getNumbers(): Call<ArrayList<Word>>

    @GET("colors")
    fun getColors(): Call<ArrayList<Word>>

    @GET("days")
    fun getDays(): Call<ArrayList<Word>>
}