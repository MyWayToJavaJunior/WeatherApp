package ru.kostikov.weatherapp.domain.datasource

import ru.kostikov.weatherapp.domain.model.Forecast
import ru.kostikov.weatherapp.domain.model.ForecastList

/**
 * @author Kostikov Aleksey
 */
interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?
    fun requestDayForecast(id: Long): Forecast?
}