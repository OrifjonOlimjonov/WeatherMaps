package uz.orifjon.weathermaps.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    fun getRetrofit(): Retrofit{
        val link= "https://api.openweathermap.org/"

        return Retrofit.Builder()
            .baseUrl(link)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}