package ru.kostikov.weatherapp.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread
import ru.kostikov.weatherapp.R
import ru.kostikov.weatherapp.domain.commands.RequestForecastCommand
import ru.kostikov.weatherapp.ui.ToolbarManager
import ru.kostikov.weatherapp.ui.adapters.ForecastListAdapter

class MainActivity : AppCompatActivity(), ToolbarManager {

    override val toolbar by lazy {find<Toolbar>(R.id.toolbar)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolbar()

        forecastList.layoutManager = LinearLayoutManager(this)

        doAsync {
            val result = RequestForecastCommand(193318).execute()
            uiThread {
                val adapter = ForecastListAdapter(result) {
                    startActivity<DetailActivity>(DetailActivity.ID to it.id,
                            DetailActivity.CITY_NAME to result.city)
                }
                forecastList.adapter = adapter
                toolbarTitle = "${result.city} (${result.country})"
            }
        }
    }

}
