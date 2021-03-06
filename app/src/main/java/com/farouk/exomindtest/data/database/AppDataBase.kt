package com.farouk.exomindtest.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.farouk.exomindtest.data.dao.AlbumDao
import com.farouk.exomindtest.data.dao.PhotoDao
import com.farouk.exomindtest.data.model.AlbumResponse
import com.farouk.exomindtest.data.model.PhotoResponse

@Database(entities = [AlbumResponse::class, PhotoResponse::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getAlbumsDao(): AlbumDao
    abstract fun getPhotosDao(): PhotoDao

    companion object {
        // visible to all other class
        @Volatile
        private var instance: AppDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDataBase(context).also {
                instance = it
            }
        }

        private fun buildDataBase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                "myDb"
            ).build()

    }
}


