package ru.kostikov.weatherapp.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import ru.kostikov.weatherapp.domain.model.ForecastList

/**
 * @author Kostikov Aleksey
 */
class ForecastListAdapter(val weekForecast: ForecastList) : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(TextView(parent.context))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(weekForecast[position]) {

            holder.textView.text = "$date - $description - $high/$low"
        }
    }

    override fun getItemCount(): Int = weekForecast.size

    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
}