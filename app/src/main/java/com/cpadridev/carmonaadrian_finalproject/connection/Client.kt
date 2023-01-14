package com.cpadridev.carmonaadrian_finalproject.connection

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Client {
    companion object{
        private const val URL:String = "http://10.0.2.2:3000/"
        var retrofit: Retrofit?= null

        fun getClient(): Retrofit? {
            if(retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            }

            return retrofit
        }
    }
}