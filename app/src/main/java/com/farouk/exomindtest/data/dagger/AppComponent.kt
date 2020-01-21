package com.farouk.exomindtest.data.dagger


import android.app.Application
import com.farouk.exomindtest.ExomindApplication
import com.farouk.exomindtest.data.database.AppDataBase
import com.farouk.exomindtest.data.repository.BaseRepository
import com.farouk.exomindtest.presenter.ui.activity.MainActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, RoomModule::class])
interface AppComponent {


    fun inject(baseRepository: BaseRepository)
    fun inject(mainActivity: MainActivity)
    fun inject(appComponent: ExomindApplication)
    fun appDataBase(): AppDataBase
    //fun userRepository(): UserRepository
    fun application(): Application

}