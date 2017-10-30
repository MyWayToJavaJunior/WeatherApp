package ru.kostikov.weatherapp.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread
import ru.kostikov.weatherapp.R
import ru.kostikov.weatherapp.domain.commands.RequestForecastCommand
import ru.kostikov.weatherapp.ui.adapters.ForecastListAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forecastListRecyclerView.layoutManager = LinearLayoutManager(this)

        doAsync {
            val result = RequestForecastCommand(193318).execute()
            uiThread {
                val adapter = ForecastListAdapter(result) {
                    startActivity<DetailActivity>(DetailActivity.ID to it.id,
                            DetailActivity.CITY_NAME to result.city)
                }
                forecastListRecyclerView.adapter = adapter
                title = "${result.city} (${result.country})"
            }
        }
    }

}
