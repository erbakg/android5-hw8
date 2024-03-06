package com.example.android5_8.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("data/2.5/forecast")
    fun getWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("units") units: String = "imperial",
        @Query("appid") appid: String = "e6e2c583cdfe47c1c509e3ffb3b9e114",
    ): retrofit2.Call<WeatherModel>

    @GET("data/2.5/weather")
    fun getWeatherByCity(
        @Query("q") city: String,
        @Query("appid") appid: String = "e6e2c583cdfe47c1c509e3ffb3b9e114",
        @Query("units") units: String = "imperial",
    ): retrofit2.Call<SearchedWeatherModel>
}