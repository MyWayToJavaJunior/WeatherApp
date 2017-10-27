package ru.kostikov.weatherapp.domain.datasource

import ru.kostikov.weatherapp.data.db.ForecastDb
import ru.kostikov.weatherapp.data.server.ForecastServer
import ru.kostikov.weatherapp.domain.model.ForecastList
import ru.kostikov.weatherapp.extensions.firstResult

/**
 * @author Kostikov Aleksey
 */
class ForecastProvider(val sources: List<ForecastDataSource> = SOURCES) {

    companion object {
        val DAY_IN_MILLIS = 1000*60*60*24
        val SOURCES = listOf(ForecastDb(), ForecastServer())
    }

    fun requestByZipCode(zipCode: Long, days: Int): ForecastList =
            sources.firstResult { requestSource(it, zipCode, days) }

    private fun requestSource(forecastSource: ForecastDataSource, zipCode: Long, days: Int ): ForecastList?{
        val res = forecastSource.requestForecastByZipCode(zipCode, todayTimeSpan())
        return if (res != null && res.size >= days) res else null
    }

    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS
}