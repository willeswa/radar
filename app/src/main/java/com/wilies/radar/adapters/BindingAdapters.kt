package com.wilies.radar.adapters

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wilies.radar.domain.*
import com.wilies.radar.utils.Utility



@BindingAdapter("setImageFromInternet")
fun setImageFromInternet(view: ImageView, icon: String?){
  icon?.let{
      Glide.with(view.context)
          .load(Utility.getIconUrl(icon))
          .into(view)
  }
}

@BindingAdapter("submitForecastList")
fun submitForecastList(recyclerView: RecyclerView, list: List<DailyWeatherDomain>?){
    val adapter = recyclerView?.adapter as ForecastRecyclerAdapter
    adapter?.setList(list?:listOf())
}


@BindingAdapter("setTempValue")
fun setTempValue(textView: TextView, temp: Double){
    textView.text = Utility.toCelsius(temp)
}


@BindingAdapter("submitList")
fun submitDailyList(recyclerView: RecyclerView?, list: List<HourlyWeatherDomain>?){
    Log.i("RECYCLEERRRRR", ""+list?.size)
    val adapter = recyclerView?.adapter as DailyWeatherRecyclerAdapter
    adapter?.setList(list?:listOf())
}


@BindingAdapter("setText")
fun setText(textView: TextView, value: Double?){
    textView.text = value.toString()
}


@BindingAdapter("timestamp", "dateFormat")
fun setDate(view: TextView, timestamp: Double, dateFormat: String){
    view.text = Utility.getFormattedDateTime(timestamp, dateFormat)
}
