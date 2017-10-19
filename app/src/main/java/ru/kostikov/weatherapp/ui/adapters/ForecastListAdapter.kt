package ru.kostikov.weatherapp.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find
import ru.kostikov.weatherapp.R
import ru.kostikov.weatherapp.domain.model.Forecast
import ru.kostikov.weatherapp.domain.model.ForecastList

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
        private val iconView = view.find<ImageView>(R.id.icon)
        private val dateView = view.find<TextView>(R.id.date)
        private val descriptionView = view.find<TextView>(R.id.description)
        private val maxTemperatureVal = view.find<TextView>(R.id.maxTemperature)
        private val minTemperatureVal = view.find<TextView>(R.id.minTemperature)

        fun bind(forecast: Forecast){
            with(forecast){
                Picasso.with(itemView.context).load(iconUrl).into(iconView)
                dateView.text = date
                descriptionView.text = description
                maxTemperatureVal.text = "$high"
                minTemperatureVal.text = "$low"
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }




}