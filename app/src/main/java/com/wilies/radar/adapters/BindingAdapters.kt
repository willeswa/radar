package com.wilies.radar.adapters

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wilies.radar.data.models.DailyWeather
import com.wilies.radar.data.models.Weather
import com.wilies.radar.data.models.WeatherDescription
import com.wilies.radar.utils.Utility



@BindingAdapter("setImageFromInternet")
fun setImageFromInternet(view: ImageView, weather: WeatherDescription?){
    Log.i("TAAG"+view.id.toString(), weather.toString())
    Glide.with(view.context)
        .load(weather?.let {
            Utility.getIconUrl(it.icon) })
        .into(view)
}

@BindingAdapter("submitForecastList")
fun submitForecastList(recyclerView: RecyclerView, list: List<DailyWeather>?){
    val adapter = recyclerView?.adapter as ForecastRecyclerAdapter
    adapter?.setList(list?:listOf())
}





@BindingAdapter("submitList")
fun submitDailyList(recyclerView: RecyclerView?, list: List<Weather>?){
    val adapter = recyclerView?.adapter as DailyWeatherRecyclerAdapter
    adapter?.setList(list?:listOf())
}

@BindingAdapter("setCardTemp")
fun setCardTemp(textView:TextView, weather:Weather?){
    weather?.let{
        textView.text = (it.temp - 273).toInt().toString()
    }
}

@BindingAdapter("setText")
fun setText(textView: TextView, value: Double?){
    textView.text = value.toString()
}


@BindingAdapter("setFormattedDate")
fun setFormattedDate(view: TextView, value: Double){
    value?.let{view.text = Utility.getDateTime(value)}
}
