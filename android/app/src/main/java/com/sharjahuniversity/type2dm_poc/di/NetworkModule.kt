package com.sharjahuniversity.type2dm_poc.di

import android.content.SharedPreferences
import com.sharjahuniversity.type2dm_poc.data.remote.APIService
import com.sharjahuniversity.type2dm_poc.data.remote.APIUrls
import com.sharjahuniversity.type2dm_poc.data.remote.AddCookiesInterceptor
import com.sharjahuniversity.type2dm_poc.data.remote.ReceivedCookiesInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(sharedPreferences: SharedPreferences): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
            .addInterceptor(AddCookiesInterceptor(sharedPreferences))
            .addInterceptor(ReceivedCookiesInterceptor(sharedPreferences))
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .baseUrl(APIUrls.BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }
}