package com.eotw95.firebasetodo.model.service.module

import com.eotw95.firebasetodo.model.service.AccountService
import com.eotw95.firebasetodo.model.service.impl.AccountServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds abstract fun provideAccountService(impl: AccountServiceImpl): AccountService
}