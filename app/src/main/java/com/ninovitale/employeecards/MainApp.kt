package com.ninovitale.employeecards

import android.app.Application
import com.ninovitale.employeecards.di.ApplicationComponent
import com.ninovitale.employeecards.di.DaggerApplicationComponent

class MainApp : Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }
}