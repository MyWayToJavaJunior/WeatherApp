package ru.kostikov.weatherapp.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_forecast.view.*
import ru.kostikov.weatherapp.R
import ru.kostikov.weatherapp.domain.model.Forecast
import ru.kostikov.weatherapp.domain.model.ForecastList
import java.text.DateFormat
import java.util.*

/**
 * @author Kostikov Aleksey
 */
class ForecastListAdapter(private val weekForecast: ForecastList,
                          private val itemClick: ((Forecast) -> Unit)): RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_forecast, parent, false)

        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(weekForecast[position])
    }

    override fun getItemCount(): Int = weekForecast.size

    class ViewHolder(view: View,
                     private val itemClick: ((Forecast) -> Unit)) : RecyclerView.ViewHolder(view){

        fun bind(forecast: Forecast){
            with(forecast){
                Picasso.with(itemView.context).load(iconUrl).into(itemView.icon)
                // every bind perform findById under hood properties, because it's not Activity/Fragment
                itemView.date.text = convertDate(date)
                itemView.description.text = description
                itemView.maxTemperature.text = "$high"
                itemView.minTemperature.text = "$low"
                itemView.setOnClickListener { itemClick(this) }
            }
        }

        private fun convertDate(date: Long): String {
            val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
            return df.format(date)
        }
    }




}