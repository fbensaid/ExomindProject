package com.farouk.exomindtest.data.dagger


import android.app.Application
import com.farouk.exomindproject.data.database.AppDataBase
import com.farouk.exomindtest.ExomindApplication
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, RoomModule::class])
interface AppComponent {


    fun inject(appComponent: ExomindApplication)
    fun appDataBase(): AppDataBase
    //fun userRepository(): UserRepository
    fun application(): Application

}