package ru.kostikov.weatherapp.extensions

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.TextView

/**
 * @author Kostikov Aleksey
 */
var TextView.textColor: Int
    get() = currentTextColor
    set(v) = setTextColor(v)

fun Context.color(res: Int): Int = ContextCompat.getColor(this, res)

fun View.slideExit() {
    if (translationY == 0f) animate().translationY(-height.toFloat())
}

fun View.slideEnter() {
    if (translationY < 0f) animate().translationY(0f)
}