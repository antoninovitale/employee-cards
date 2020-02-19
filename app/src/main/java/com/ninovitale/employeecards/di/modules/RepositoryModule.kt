package com.ninovitale.employeecards.di.modules

import com.ninovitale.employeecards.api.GistsApi
import com.ninovitale.employeecards.data.GistsRepository
import com.ninovitale.employeecards.data.RemoteDataSource
import com.ninovitale.employeecards.data.RemoteGistsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryModule {
    @Provides
    @Singleton
    @JvmStatic
    fun provideRemoteDataSource(api: GistsApi) = RemoteDataSource(api)

    @Provides
    @JvmStatic
    @Singleton
    fun provideEmployeeRepository(remoteDataSource: RemoteDataSource): GistsRepository =
        RemoteGistsRepository(remoteDataSource)
}