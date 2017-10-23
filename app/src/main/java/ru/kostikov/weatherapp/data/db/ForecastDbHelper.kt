package ru.kostikov.weatherapp.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*
import ru.kostikov.weatherapp.ui.App

/**
 * @author Kostikov Aleksey
 */
class ForecastDbHelper(ctx: Context = App.instance): ManagedSQLiteOpenHelper(ctx, ForecastDbHelper.DB_NAME, null, ForecastDbHelper.DB_VERSION ) {

    companion object {
        val DB_NAME = "forecast.db"
        val DB_VERSION = 1
        val instance by lazy { ForecastDbHelper() }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(CityForecastTable.NAME, true,
                CityForecastTable.ID to INTEGER + PRIMARY_KEY,
                CityForecastTable.CITY to TEXT,
                CityForecastTable.COUNTRY to TEXT)

        db?.createTable(DayForecastTable.NAME,  true,
                DayForecastTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                DayForecastTable.DATE to INTEGER,
                DayForecastTable.DESCRIPTION to TEXT,
                DayForecastTable.HIGH to INTEGER,
                DayForecastTable.LOW to INTEGER,
                DayForecastTable.ICON_URL to INTEGER,
                DayForecastTable.CITY_ID to INTEGER)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.dropTable(CityForecastTable.NAME)
        db?.dropTable(DayForecastTable.NAME)
        onCreate(db)
    }
}