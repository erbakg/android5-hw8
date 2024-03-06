package com.example.android5_8.di

import com.example.android5_8.remote.Forecast
import com.example.android5_8.remote.SearchedWeatherModel
import com.example.android5_8.remote.WeatherApi
import com.example.android5_8.remote.WeatherModel
import javax.inject.Inject

class Repository @Inject constructor(private val api: WeatherApi) {
    fun getWeather(lat: String, lon: String): WeatherModel {
        return api.getWeather(lat, lon).execute().body()!!
    }
    fun getWeatherByCity(city: String): SearchedWeatherModel {
        return api.getWeatherByCity(city).execute().body()!!
    }
}