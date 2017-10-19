package ru.kostikov.weatherapp.domain.commands

import ru.kostikov.weatherapp.data.ForecastRequest
import ru.kostikov.weatherapp.domain.mappers.ForecastDataMapper
import ru.kostikov.weatherapp.domain.model.ForecastList

/**
 * @author Kostikov Aleksey
 */
class RequestForecastCommand(val zipCode: String): Command<ForecastList> {
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }
}