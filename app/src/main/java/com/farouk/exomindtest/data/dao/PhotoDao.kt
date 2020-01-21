package com.farouk.exomindtest.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.farouk.exomindtest.data.model.PhotoResponse


@Dao
 interface PhotoDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insertPhotos(albums: MutableList<PhotoResponse>):LongArray?

    @Query("SELECT * FROM photoresponse WHERE albumId= :albumId")
     fun getPhotosByAlbum(albumId:String):List<PhotoResponse>

}