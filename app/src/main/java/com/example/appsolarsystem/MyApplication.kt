package com.example.appsolarsystem

import android.app.Application
import com.example.appsolarsystem.data.AppContainer
import com.example.appsolarsystem.data.DefaultAppContainer

class MyApplication : Application(){
    lateinit var container : AppContainer
    override fun onCreate(){
        super.onCreate()
        container = DefaultAppContainer(context = applicationContext)
    }
}