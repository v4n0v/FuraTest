package com.ezmobdev.furatest.di.modules

import android.util.Log
import com.ezmobdev.furatest.api.ApiFactory
import com.ezmobdev.furatest.components.FURA_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
open class ApiFactoryModule {
    @Singleton
    @Provides
    fun apiFactory(
        @Named("okhttp_logging") client: OkHttpClient,
        @Named("gson") gsonConverterFactory: GsonConverterFactory,
        @Named("fura_url") url: String
    ): ApiFactory {
        return ApiFactory(client, gsonConverterFactory,  url)

    }

    @Provides
    @Named("okhttp_logging")
    fun getOkHTTPClient(): OkHttpClient {
        val c = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            Log.d("OkHttpLogger", it)
        })

        logging.level = HttpLoggingInterceptor.Level.BASIC
        c.addInterceptor(logging)
        return c.build()
    }

    @Provides
    @Named("gson")
    fun getGsonConverterFactory(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)

    @Provides
    fun gson() = GsonBuilder()
        .setLenient()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        .create()

    @Provides
    @Named("fura_url")
    open fun url() = FURA_URL
}