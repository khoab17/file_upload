package com.abdullah.fileuploader.di

import android.app.Application
import android.content.Context
import com.abdullah.fileuploader.api.NetWorkApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Provides
import dagger.hilt.InstallIn
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import dagger.Module
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun providesContext(application: Application): Context = application.applicationContext

    @Singleton
    @Provides
    fun provideGithubService(application: Application): NetWorkApi {
        val builder = OkHttpClient().newBuilder()
        builder.readTimeout(60.toLong(), TimeUnit.SECONDS)
        builder.connectTimeout(60.toLong(), TimeUnit.SECONDS)

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(loggingInterceptor)
       // builder.addInterceptor(ChuckerInterceptor.Builder(application.applicationContext).build())
        builder.addInterceptor(
            Interceptor { chain ->
                val originalRequest = chain.request()
                // Request customization: add request headers
                val requestBuilder =
                    originalRequest.newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .method(originalRequest.method, originalRequest.body)

                val request = requestBuilder.build()
                chain.proceed(request)
            }
        )


        val client = builder.build()

        val gson: Gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl("API_REST_URL")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(NetWorkApi::class.java)
    }

/*
    @Singleton
    @Provides
    fun providesImageUploadRepository(
        service: NetWorkApi
    ): ImageUploadRepository = ImageUploadDataRepository(service)*/
}
