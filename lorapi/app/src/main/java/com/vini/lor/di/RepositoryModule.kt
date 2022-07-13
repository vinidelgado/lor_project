package com.vini.lor.di

import com.vini.lor.data.repository.LorRepositoryImpl
import com.vini.lor.domain.LorRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindLorRepository(
        weatherRepositoryImpl: LorRepositoryImpl
    ): LorRepository
}