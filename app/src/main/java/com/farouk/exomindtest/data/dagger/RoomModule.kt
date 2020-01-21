package com.farouk.exomindtest.data.dagger

import android.app.Application
import dagger.Provides
import androidx.room.Room
import com.farouk.exomindtest.data.database.AppDataBase

import dagger.Module
import javax.inject.Singleton


@Module
class RoomModule(mApplication: Application) {

    private val appDatabase: AppDataBase =
        Room.databaseBuilder(mApplication, AppDataBase::class.java!!, "demo-db").build()

    @Singleton
    @Provides
    internal fun providesRoomDatabase(): AppDataBase {
        return appDatabase
    }
    
}