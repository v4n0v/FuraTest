package com.ezmobdev.furatest.di.modules

import com.ezmobdev.furatest.api.ApiFactory
import com.ezmobdev.furatest.repository.FuraRepository
import com.ezmobdev.furatest.repository.IPointsRepo
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [ApiFactoryModule::class])
class FuraRepoModule {
    @Provides
    @Singleton
    fun getFuraRepository(@Named("def")repo: IPointsRepo):IPointsRepo{
        return repo
    }

    @Provides
    @Named("def")
    fun getFuraRepo(apiFactory:ApiFactory):IPointsRepo{
        return FuraRepository(apiFactory)
    }

}