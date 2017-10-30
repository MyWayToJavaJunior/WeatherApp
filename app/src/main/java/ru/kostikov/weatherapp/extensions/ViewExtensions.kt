package ru.kostikov.weatherapp.extensions

import android.content.Context
import android.support.v4.content.ContextCompat
import android.widget.TextView

/**
 * @author Kostikov Aleksey
 */
var TextView.textColor: Int
    get() = currentTextColor
    set(v) = setTextColor(v)

fun Context.color(res: Int): Int = ContextCompat.getColor(this, res)