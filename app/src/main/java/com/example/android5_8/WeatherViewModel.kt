package com.example.android5_8

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android5_8.di.Repository
import com.example.android5_8.remote.Forecast
import com.example.android5_8.remote.SearchedWeatherModel
import com.example.android5_8.remote.WeatherModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    val cityWeatherLiveData = MutableLiveData<MutableList<SearchedWeatherModel?>?>(null)
    val weatherLiveData = MutableLiveData<WeatherModel>(null)

    fun getWeatherByCity(city: String) {
        val oldList = cityWeatherLiveData.value
        if (oldList == null) {
            cityWeatherLiveData.value = mutableListOf(repository.getWeatherByCity(city))
        } else {
            oldList.add(repository.getWeatherByCity(city))
            cityWeatherLiveData.value = oldList
        }


    }

    fun getWeather(lat: String, lon: String) {
        weatherLiveData.value = repository.getWeather(lat, lon)

    }
}