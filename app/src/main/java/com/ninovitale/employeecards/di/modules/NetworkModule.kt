package com.ninovitale.employeecards.di.modules

import com.ninovitale.employeecards.di.subcomponents.NetworkSubComponent
import com.ninovitale.employeecards.BuildConfig
import com.ninovitale.employeecards.api.GistsApiService
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import se.ansman.kotshi.KotshiJsonAdapterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(subcomponents = [NetworkSubComponent::class])
object NetworkModule {
    @Provides
    @Singleton
    @JvmStatic
    fun provideMoshi(): Moshi = Moshi.Builder().add(ApplicationJsonAdapterFactory.INSTANCE).build()

    @Provides
    @Singleton
    @JvmStatic
    fun provideService(client: OkHttpClient, moshi: Moshi) = GistsApiService(moshi, client)

    @Provides
    @Singleton
    @JvmStatic
    fun provideApi(service: GistsApiService) = service.createApi()

    @Provides
    @Singleton
    @JvmStatic
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = when {
            BuildConfig.DEBUG -> HttpLoggingInterceptor.Level.BODY
            else -> HttpLoggingInterceptor.Level.NONE
        }
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }
}

@KotshiJsonAdapterFactory
abstract class ApplicationJsonAdapterFactory : JsonAdapter.Factory {
    companion object {
        val INSTANCE: ApplicationJsonAdapterFactory = KotshiApplicationJsonAdapterFactory
    }
}