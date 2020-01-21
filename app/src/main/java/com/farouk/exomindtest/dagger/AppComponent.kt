package com.farouk.exomindtest.dagger


import android.app.Application
import com.farouk.exomindproject.data.database.AppDataBase
import com.farouk.exomindtest.ExomindApplication
import com.farouk.exomindtest.data.dagger.AppModule
import com.farouk.exomindtest.data.dagger.RoomModule
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