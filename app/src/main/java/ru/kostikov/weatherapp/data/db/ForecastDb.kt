package ru.kostikov.weatherapp.data.db

import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import ru.kostikov.weatherapp.domain.datasource.ForecastDataSource
import ru.kostikov.weatherapp.domain.model.Forecast
import ru.kostikov.weatherapp.domain.model.ForecastList
import ru.kostikov.weatherapp.extensions.*

/**
 * @author Kostikov Aleksey
 */
class ForecastDb: ForecastDataSource {
    val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance
    val dataMapper: DbDataMapper = DbDataMapper()

    override fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastDbHelper.use {
        val dailyRequest = "${DayForecastTable.CITY_ID} = ? AND ${DayForecastTable.DATE} >= ?"
        val dailyForecast = select(DayForecastTable.NAME)
                .whereSimple(dailyRequest, zipCode.toString(), date.toString())
                .parseList{DayForecast(HashMap(it))}

        val city = select(CityForecastTable.NAME)
                .whereSimple("${CityForecastTable.ID} = ?", zipCode.toString())
                .parseOpt{CityForecast(HashMap(it), dailyForecast)}

        if (city != null) dataMapper.convertToDomain(city) else null
    }

    override fun requestDayForecast(id: Long): Forecast? = forecastDbHelper.use{
        val forecast = select(DayForecastTable.NAME).byId(id)
                .parseOpt{DayForecast(HashMap(it))}

        if (forecast != null) dataMapper.convertDayToDomain(forecast) else null
    }

    fun saveForecast(forecast: ForecastList) = forecastDbHelper.use{
        clear(CityForecastTable.NAME)
        clear(DayForecastTable.NAME)

        with(dataMapper.convertFromDomain(forecast)) {
            insert(CityForecastTable.NAME, *map.toVarargArray())
            dailyForecast.forEach {
                insert(DayForecastTable.NAME, *it.map.toVarargArray())
                    }
            }
    }


}