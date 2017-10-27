package ru.kostikov.weatherapp.domain.commands

import ru.kostikov.weatherapp.domain.datasource.ForecastProvider
import ru.kostikov.weatherapp.domain.model.ForecastList

/**
 * @author Kostikov Aleksey
 */
class RequestForecastCommand(val zipCode: Long,
                             val forecastProvider: ForecastProvider = ForecastProvider()): Command<ForecastList> {
    companion object {
        val DAYS = 7
    }

    override fun execute(): ForecastList {
        return forecastProvider.requestByZipCode(zipCode, DAYS)
    }
}