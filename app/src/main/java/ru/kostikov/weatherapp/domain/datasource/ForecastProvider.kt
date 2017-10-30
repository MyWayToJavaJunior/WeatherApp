package ru.kostikov.weatherapp.domain.datasource

import ru.kostikov.weatherapp.data.db.ForecastDb
import ru.kostikov.weatherapp.data.server.ForecastServer
import ru.kostikov.weatherapp.domain.model.Forecast
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

    fun requestForecast(id: Long): Forecast = requestToSources { it.requestDayForecast(id) }

    fun requestByZipCode(zipCode: Long, days: Int): ForecastList = requestToSources {
        val res = it.requestForecastByZipCode(zipCode, todayTimeSpan())
        if (res != null && res.size >= days) res else null
    }

    private fun <T:Any> requestToSources(f: (ForecastDataSource) -> T?): T =
            sources.firstResult{f(it)}

    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS
}