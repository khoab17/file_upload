package com.sharjahuniversity.type2dm_poc.di

import com.sharjahuniversity.type2dm_poc.data.local.room.Type2DMDao
import com.sharjahuniversity.type2dm_poc.data.remote.APIService
import com.sharjahuniversity.type2dm_poc.data.repository.Type2DMRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideType2DMRepository(apiService: APIService, dao: Type2DMDao): Type2DMRepository {
        return Type2DMRepository(apiService, dao)
    }
}