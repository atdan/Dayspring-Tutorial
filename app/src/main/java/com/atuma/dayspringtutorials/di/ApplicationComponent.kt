package com.atuma.dayspringtutorials.di

import android.app.Application
import com.atuma.dayspringtutorials.ui.home.HomeFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class])
interface ApplicationComponent {
    fun inject(fragment: HomeFragment)

    @Component.Builder
    interface Builder {
        fun build(): ApplicationComponent

        @BindsInstance
        fun bindApplication(context: Application): Builder
    }


}