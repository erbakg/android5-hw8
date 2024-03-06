package com.example.android5_8.remote

data class WeatherModel(
    val cod: String,
    val message: Int,
    val cnt: Int,
    val list: List<Forecast>,
    val city: City
)

data class Forecast(
    val main: Main,
    val weather: List<Weather>,
    val dt_txt: String
)

data class Main(
    val temp: Double,
    val temp_min: Double,
    val temp_max: Double,
)

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)


data class City(
    val id: Int,
    val name: String,
    val coord: Coord,
    val country: String,
    val population: Int,
    val timezone: Int,
    val sunrise: Long,
    val sunset: Long
)

data class Coord(
    val lat: Double,
    val lon: Double
)