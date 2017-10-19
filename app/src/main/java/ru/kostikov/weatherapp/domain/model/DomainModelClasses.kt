package ru.kostikov.weatherapp.domain.model

/**
 * @author Kostikov Aleksey
 */

data class ForecastList(val city: String, val country: String,
                            val dailyForecast:List<Forecast>)

data class Forecast(val date: String, val description: String, val high: Int,
                        val low: Int)