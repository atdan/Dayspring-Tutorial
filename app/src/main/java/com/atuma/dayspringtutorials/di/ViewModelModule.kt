package com.atuma.dayspringtutorials.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.atuma.dayspringtutorials.ui.home.HomeViewModel
import com.wadud.basicview.di.TodoViewModelFactory
import com.wadud.basicview.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap




@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: TodoViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindViewModel(viewModel: HomeViewModel): ViewModel

}