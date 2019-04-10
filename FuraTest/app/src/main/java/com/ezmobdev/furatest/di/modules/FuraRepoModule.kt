package com.ezmobdev.furatest.di.modules

import com.ezmobdev.furatest.api.ApiFactory
import com.ezmobdev.furatest.repository.FuraRepository
import com.ezmobdev.furatest.repository.IRepo
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [ApiFactoryModule::class])
class FuraRepoModule {
    @Provides
    @Singleton
    fun getFuraRepository(@Named("fura")repo: IRepo):IRepo{
        return repo
    }

    @Provides
    @Named("fura")
    fun getFuraRepo(apiFactory:ApiFactory):IRepo{
        return FuraRepository(apiFactory)
    }

}