package com.ezmobdev.furatest.di.modules

import com.ezmobdev.furatest.repository.ILocalRepo
import com.ezmobdev.furatest.repository.LocationRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton


@Module
class LocationModule {

    @Provides
    @Singleton
    fun getLocationRepository(@Named("def")repo:ILocalRepo):ILocalRepo{
        return repo
    }

    @Provides
    @Named("def")
    fun getDefaultLocalRepo():ILocalRepo{
        return LocationRepository()
    }

}