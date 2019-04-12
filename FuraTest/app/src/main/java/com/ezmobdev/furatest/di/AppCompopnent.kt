package com.ezmobdev.furatest.di

import com.ezmobdev.furatest.App
import com.ezmobdev.furatest.di.modules.ApiFactoryModule
import com.ezmobdev.furatest.di.modules.FuraRepoModule
import com.ezmobdev.furatest.di.modules.LocationModule
import com.ezmobdev.furatest.viewModels.MainViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        ApiFactoryModule::class,
        FuraRepoModule::class,
        LocationModule::class
    ]
)
@Singleton
interface AppComponent {
    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        @BindsInstance
        fun application(appModule: App): Builder
    }

    fun inject(vm: MainViewModel)
}