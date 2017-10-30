package ru.kostikov.weatherapp.domain.commands

import ru.kostikov.weatherapp.domain.datasource.ForecastProvider
import ru.kostikov.weatherapp.domain.model.Forecast

/**
 * @author Kostikov Aleksey
 */
class RequestDayForecastCommand(val id: Long,
                                val forecastProvider: ForecastProvider = ForecastProvider()): Command<Forecast> {

    override fun execute() = forecastProvider.requestForecast(id)
}