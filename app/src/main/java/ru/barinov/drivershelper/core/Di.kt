package ru.barinov.drivershelper.core

import android.content.*
import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.barinov.drivershelper.data.*
import ru.barinov.drivershelper.data.localDataBase.*
import ru.barinov.drivershelper.ui.*

val appModule = module {

    single<AppDataBase> {
        Room.databaseBuilder(get(), AppDataBase::class.java, "local_database").allowMainThreadQueries().build()
    }

    single<ProfilesDAO>{
        get<AppDataBase>().getProfileDao()
    }

    single  <SharedPreferences> {
        androidApplication().getSharedPreferences(ActivityMain.sharedPreferencesName, Context.MODE_PRIVATE)
    }

    single<AccountDataDAO>{
        get<AppDataBase>().getAccountMovementDao()
    }

    single<RoutesDAO>{
        get<AppDataBase>().getRoutesDao()
    }

    single<ProfilesRepository> {
        ProfilesRepository( get() )
    }

    single<AccountBalanceRepository> {
        AccountBalanceRepository(get())
    }

    viewModel<StatisticFragmentViewModel> {parameter->
        StatisticFragmentViewModel(get(), get(), parameter.get(), androidApplication().resources.displayMetrics.density )
    }

    viewModel<BalanceChangeDialogViewModel> {
        BalanceChangeDialogViewModel(get())
    }

    viewModel<ProfileCreationViewModel> {
        ProfileCreationViewModel(get())
    }
}