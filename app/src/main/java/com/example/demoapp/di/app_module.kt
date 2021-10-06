package com.example.demoapp.di

import com.example.demoapp.domain.DemoRepository
import com.example.demoapp.utils.coroutines.ApplicationSchedulerProvider
import com.example.demoapp.utils.coroutines.SchedulerProvider
import com.example.demoapp.views.dashboard.products.ProductsViewModel
import com.example.demoapp.views.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/** App Components for Dependency Injection **/

val appModule = module {
    viewModel { MainViewModel() }
//    viewModel { ProductsViewModel(get(),get()) }

//    single<DemoRepository>(createdAtStart = true) { DemoRepositoryImpl(get()) }

    // SchedulerProvider
    single<SchedulerProvider>(createdAtStart = true) { ApplicationSchedulerProvider() }
}

//val resourceApp = listOf(appModule, remoteDatasourceModule)