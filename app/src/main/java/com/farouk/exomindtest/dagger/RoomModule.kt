package com.farouk.exomindtest.dagger

import android.app.Application
import dagger.Provides
import androidx.room.Room
import com.farouk.exomindproject.data.database.AppDataBase
import com.farouk.exomindproject.data.remoteApi.ApiInterface
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