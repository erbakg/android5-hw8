package com.example.android5_8.remote

data class SearchedWeatherModel(
    val coord: Coord,
   val weather: List<Weather>,
   val base: String,
    val main: Main,
   val visibility: Int,
    val dt: Long,
    val timezone: Int,
    val id: Int,
     val name: String,
    val cod: Int
)
