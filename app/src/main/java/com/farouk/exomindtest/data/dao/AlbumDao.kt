package com.farouk.exomindtest.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.farouk.exomindtest.data.model.AlbumResponse


@Dao
 interface AlbumDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insertAlbums(albums: MutableList<AlbumResponse>):LongArray?

    @Query("SELECT * FROM albumresponse WHERE userId= :idUser")
     fun getAlbumByUser(idUser:String):List<AlbumResponse>

}