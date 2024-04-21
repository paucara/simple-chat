package com.example.simplechat.data.module

import com.example.simplechat.data.service.AccountService
import com.example.simplechat.data.service.StorageService
import com.example.simplechat.data.service.impl.AccountServiceImpl
import com.example.simplechat.data.service.impl.StorageServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule{
    @Binds
    abstract fun providesAccountService(impl : AccountServiceImpl): AccountService

    @Binds
    abstract fun providesStorageServices(impl : StorageServiceImpl) : StorageService
}