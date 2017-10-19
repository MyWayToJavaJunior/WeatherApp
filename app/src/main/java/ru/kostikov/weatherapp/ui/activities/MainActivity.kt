package ru.kostikov.weatherapp.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import ru.kostikov.weatherapp.R
import ru.kostikov.weatherapp.domain.commands.RequestForecastCommand
import ru.kostikov.weatherapp.domain.model.Forecast
import ru.kostikov.weatherapp.ui.adapters.ForecastListAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val forecastList = findViewById<RecyclerView>(R.id.forecast_list)
        forecastList.layoutManager = LinearLayoutManager(this)

        doAsync {
            val result = RequestForecastCommand("94043").execute()
            uiThread {
                forecastList.adapter = ForecastListAdapter(result,
                        object : ForecastListAdapter.OnItemClickListener{
                            override fun invoke(forecast: Forecast) {
                                toast(forecast.date)
                            }
                        })
                }
        }
    }
}
