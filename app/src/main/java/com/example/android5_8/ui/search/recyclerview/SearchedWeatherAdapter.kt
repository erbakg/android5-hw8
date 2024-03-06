package com.example.android5_8.ui.home.bottomSheet.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android5_8.databinding.ItemSearchBinding
import com.example.android5_8.remote.SearchedWeatherModel
import com.example.android5_8.utils.DiffUtilCallback
import java.time.format.DateTimeFormatter


class SearchedWeatherAdapter(
    private var weatherList: ArrayList<SearchedWeatherModel>,
) :
    RecyclerView.Adapter<SearchedWeatherAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(weatherList.reversed()[position])
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    class ViewHolder(var itemBinding: ItemSearchBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindItem(weather: SearchedWeatherModel) {
            with(itemBinding) {
                Log.d("haha", "bindItem: ${String.format("%.0f",weather.main.temp)}")
                tvTemp.text = "${String.format("%.0f", weather.main.temp)}°"
                tvCity.text = weather.name
                tvHigh.text = "${String.format("%.0f", weather.main.temp_max)}°"
                tvLow.text = "${String.format("%.0f", weather.main.temp_min)}°"
                tvDesc.text = weather.weather[0].description
            }
        }
    }
    // add new data
    fun setNewData(newData: List<SearchedWeatherModel>) {
        val diffCallback = DiffUtilCallback(weatherList, newData)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        weatherList.clear()
        weatherList.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
    }
}