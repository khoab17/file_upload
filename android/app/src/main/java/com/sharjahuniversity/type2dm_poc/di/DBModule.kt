package com.sharjahuniversity.type2dm_poc.di

import android.content.Context
import androidx.room.Room
import com.sharjahuniversity.type2dm_poc.data.local.room.AppDatabase
import com.sharjahuniversity.type2dm_poc.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Singleton
    @Provides
    fun provideRoomDB(@ApplicationContext applicationContext: Context) = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java, Constants.DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideDAO(db: AppDatabase) = db.type2DMDao()
}