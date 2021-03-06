package ru.kostikov.weatherapp.ui

import android.support.v7.graphics.drawable.DrawerArrowDrawable
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import org.jetbrains.anko.toast
import ru.kostikov.weatherapp.R
import ru.kostikov.weatherapp.extensions.slideEnter
import ru.kostikov.weatherapp.extensions.slideExit
import ru.kostikov.weatherapp.ui.utils.ctx

/**
 * @author Kostikov Aleksey
 */
interface ToolbarManager {
    val toolbar: Toolbar
    var toolbarTitle: String
        get() = toolbar.title.toString()
        set(value){
            toolbar.title = value
        }

    fun initToolbar(){
        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.action_settings -> App.instance.toast("Settings")
                else -> App.instance.toast("Unknown option")
            }
            true
        }
    }

    fun enableHomeAsUp(up: () -> Unit){
        toolbar.navigationIcon = createUpDrawable()
        toolbar.setNavigationOnClickListener { up() }
    }

    private fun createUpDrawable() = with(DrawerArrowDrawable(toolbar.ctx)){
        progress = 1f
        this
    }

    fun attachToScroll(recyclerView: RecyclerView){
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (dy > 0) toolbar.slideExit() else toolbar.slideEnter()
            }
        })
    }
}