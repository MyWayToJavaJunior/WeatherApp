package ru.kostikov.weatherapp.data.server

import ru.kostikov.weatherapp.data.Forecast
import ru.kostikov.weatherapp.data.ForecastResult
import ru.kostikov.weatherapp.domain.model.ForecastList
import java.util.*
import java.util.concurrent.TimeUnit
import ru.kostikov.weatherapp.domain.model.Forecast as ModelForecast

/**
 * @author Kostikov Aleksey
 */
class ServerDataMapper {

    fun convertFromDataModel(zipCode: Long, forecast: ForecastResult): ForecastList{
        return ForecastList(zipCode, forecast.city.name, forecast.city.country, convertForecastListToDomain(forecast.list) )
    }

    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast>{
        return list.mapIndexed { i, forecast ->
            val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(i.toLong())
            convertForecastItemToDomain(forecast.copy(dt = dt))
        }
    }

    private fun convertForecastItemToDomain(forecast: Forecast) = with(forecast){
        ModelForecast(forecast.dt, forecast.weather[0].description,
                forecast.temp.max.toInt(), forecast.temp.min.toInt(),generateIconUrl(forecast.weather[0].icon) )
    }

    private fun generateIconUrl(iconCode: String): String
            = "http://openweathermap.org/img/w/$iconCode.png"
}

