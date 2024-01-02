package com.example.appsolarsystem

import android.app.Application
import com.example.appsolarsystem.data.AppContainer
import com.example.appsolarsystem.data.DefaultAppContainer
/**
 * Custom application class for initializing and managing the application-wide components.
 */
class MyApplication : Application() {

    /**
     * The application container that manages dependencies and global instances.
     */
    lateinit var container: AppContainer

    /**
     * Called when the application is starting, before any other application objects have been created.
     * Initializes the application container.
     */
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(context = applicationContext)
    }
}
