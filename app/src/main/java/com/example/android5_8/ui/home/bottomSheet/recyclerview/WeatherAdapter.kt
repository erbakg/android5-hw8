package com.example.android5_8.ui.home.bottomSheet.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.core.view.marginEnd
import androidx.recyclerview.widget.RecyclerView
import com.example.android5_8.databinding.ItemHomeBottomSheetBinding
import com.example.android5_8.remote.Forecast
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class WeatherAdapter(
    private val heroList: List<Forecast>,
) :
    RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            ItemHomeBottomSheetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(heroList[position], position == heroList.size - 1)

        if (position == heroList.size - 1) {
            val layoutParams =
                RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
                )
            layoutParams.setMargins(26, 0, 32, 0)
            holder.itemView.layoutParams = layoutParams
        }
    }

    override fun getItemCount(): Int {
        return heroList.size
    }

    class ViewHolder(var itemBinding: ItemHomeBottomSheetBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindItem(weather: Forecast, isLast: Boolean) {
            with(itemBinding) {
                val format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                val formattedTime = DateTimeFormatter.ofPattern("hh:mm")
                val formattedDate = DateTimeFormatter.ofPattern("dd.MM")
                val date = LocalDateTime.parse(weather.dt_txt, format)
                tvDate.text = "${date.format(formattedDate)}"
                tvTime.text = "${date.format(formattedTime)}"
                tvTemp.text = weather.main.temp.toString()
            }
        }
    }
}