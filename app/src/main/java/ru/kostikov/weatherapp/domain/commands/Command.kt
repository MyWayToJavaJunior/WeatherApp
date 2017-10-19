package ru.kostikov.weatherapp.domain.commands

/**
 * @author Kostikov Aleksey
 */
interface Command<out T> {
    fun execute(): T
}