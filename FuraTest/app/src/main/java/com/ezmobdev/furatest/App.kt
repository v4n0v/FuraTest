package com.ezmobdev.furatest

import android.app.Application
import com.ezmobdev.furatest.di.AppComponent
import com.ezmobdev.furatest.di.DaggerAppComponent

class App:Application(){

    private lateinit var appComponent: AppComponent
    fun getAppComponent(): AppComponent {
        return appComponent
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    companion object {
        lateinit var instance: App
    }
}