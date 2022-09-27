package uz.orifjon.weathermaps.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import uz.orifjon.weathermaps.models.WeatherInformation

interface ApiService {

    @GET("data/2.5/weather")
    fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String
    ): Call<WeatherInformation>

}