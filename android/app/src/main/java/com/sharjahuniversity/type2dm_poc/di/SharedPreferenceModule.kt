package com.sharjahuniversity.type2dm_poc.di

import android.content.Context
import android.content.SharedPreferences
import com.sharjahuniversity.type2dm_poc.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferenceModule {

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext applicationContext: Context): SharedPreferences =
        applicationContext.getSharedPreferences(
            Constants.SharedPreferencesFileName,
            Context.MODE_PRIVATE
        )
}