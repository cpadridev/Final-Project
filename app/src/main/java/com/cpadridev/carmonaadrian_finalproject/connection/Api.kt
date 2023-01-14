package com.cpadridev.carmonaadrian_finalproject.connection

import com.cpadridev.carmonaadrian_finalproject.model.Number
import com.cpadridev.carmonaadrian_finalproject.model.Color
import com.cpadridev.carmonaadrian_finalproject.model.Day
import retrofit2.Call
import retrofit2.http.*

interface Api {
    // NUMBER
    @GET("numbers")
    fun getNumbers(): Call<ArrayList<Number>>

    @FormUrlEncoded
    @POST("numbers")
    fun saveNumbers(@Field("spanish") spanish: String,
                     @Field("english") english: String,): Call<Number>

    @PUT("numbers/{id}")
    fun updateNumber(@Path("id") id: Int, @Body number: Number): Call<Number>

    @DELETE("numbers/{id}")
    fun deleteNumber(@Path("id") id: Int): Call<Number>

    // COLOR
    @GET("colors")
    fun getColors(): Call<ArrayList<Color>>

    @FormUrlEncoded
    @POST("colors")
    fun saveColors(@Field("spanish") spanish: String,
                     @Field("english") english: String,): Call<Color>

    @PUT("colors/{id}")
    fun updateColor(@Path("id") id: Int, @Body color: Color): Call<Color>

    @DELETE("colors/{id}")
    fun deleteColor(@Path("id") id: Int): Call<Color>
    
    // DAY
    @GET("days")
    fun getDays(): Call<ArrayList<Day>>

    @FormUrlEncoded
    @POST("days")
    fun saveDays(@Field("spanish") spanish: String,
                     @Field("english") english: String,): Call<Day>

    @PUT("days/{id}")
    fun updateDay(@Path("id") id: Int, @Body Day: Day): Call<Day>

    @DELETE("days/{id}")
    fun deleteDay(@Path("id") id: Int): Call<Day>
}