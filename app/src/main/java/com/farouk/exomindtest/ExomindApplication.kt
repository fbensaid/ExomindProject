
package com.farouk.exomindtest

import android.app.Application
import android.content.Context
import com.farouk.exomindtest.data.dagger.AppComponent
import com.farouk.exomindtest.data.dagger.AppModule
import com.farouk.exomindtest.data.dagger.DaggerAppComponent
import com.farouk.exomindtest.data.dagger.RoomModule

class ExomindApplication : Application() {

    companion object {
        lateinit var appComponent: AppComponent
        lateinit var instance: Context
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initDI()
    }

    private fun initDI() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .roomModule(RoomModule(this))
            .build()
    }
}


