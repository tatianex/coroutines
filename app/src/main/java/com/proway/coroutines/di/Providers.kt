package com.proway.coroutines.di

import com.proway.coroutines.service.GithubService
import com.proway.coroutines.service.RetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class Providers {

    @Provides
    fun provideGithubService(): GithubService = RetrofitService.getGithubServices()
}