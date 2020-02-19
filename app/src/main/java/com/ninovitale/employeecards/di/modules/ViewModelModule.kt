package com.ninovitale.employeecards.di.modules

import androidx.lifecycle.ViewModelProvider
import com.ninovitale.employeecards.data.GistsRepository
import com.ninovitale.employeecards.ui.EmployeesMapper
import com.ninovitale.employeecards.ui.EmployeesViewModelFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object ViewModelModule {
    @Provides
    @JvmStatic
    @Singleton
    fun provideMapper(moshi: Moshi) = EmployeesMapper(moshi)

    @Provides
    @JvmStatic
    @Singleton
    fun provideViewModelFactory(
        employeesMapper: EmployeesMapper,
        gistsRepository: GistsRepository
    ): ViewModelProvider.Factory = EmployeesViewModelFactory(employeesMapper, gistsRepository)
}