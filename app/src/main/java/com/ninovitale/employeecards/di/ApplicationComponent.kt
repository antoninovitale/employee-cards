package com.ninovitale.employeecards.di

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.ninovitale.employeecards.MainApp
import com.ninovitale.employeecards.data.GistsRepository
import com.ninovitale.employeecards.di.modules.NetworkModule
import com.ninovitale.employeecards.di.modules.RepositoryModule
import com.ninovitale.employeecards.di.modules.ViewModelModule
import com.ninovitale.employeecards.di.subcomponents.NetworkSubComponent
import com.ninovitale.employeecards.ui.MainActivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ViewModelModule::class, RepositoryModule::class])
interface ApplicationComponent : AndroidInjector<MainApp> {
    val viewModelFactory: ViewModelProvider.Factory

    val repository: GistsRepository

    val networkSubComponentFactory: NetworkSubComponent.Factory

    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): ApplicationComponent
    }
}