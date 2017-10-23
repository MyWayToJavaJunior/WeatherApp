package ru.kostikov.weatherapp.ui

import android.app.Application
import ru.kostikov.weatherapp.ui.utils.DelegateExt

/**
 * @author Kostikov Aleksey
 */
class App: Application()  {

    companion object {
        var instance: App by DelegateExt.notNullSingleValue<App>()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}