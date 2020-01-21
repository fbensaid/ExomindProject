package com.farouk.exomindproject.data.repository

import com.farouk.exomindproject.ExomindApplication
import com.farouk.exomindproject.data.dao.AlbumDao
import com.farouk.exomindproject.data.dao.PhotoDao
import com.farouk.exomindproject.data.database.AppDataBase
import com.farouk.exomindproject.data.model.AlbumResponse
import com.farouk.exomindproject.data.model.user.UserResponse
import com.farouk.exomindproject.data.remoteApi.ApiInterface

class AlbumsRepository(private val api: ApiInterface) : BaseRepository() {

    private var albumDao: AlbumDao = AppDataBase.invoke(ExomindApplication.instance!!).getAlbumsDao()

    //get latest news using safe api call
    suspend fun getAlbumsByUser(userId:String): MutableList<AlbumResponse>? {
        var dbUserAlbum=albumDao.getAlbumByUser(userId)
        return if(!dbUserAlbum.isNullOrEmpty()){
            albumDao.getAlbumByUser(userId)!!.toMutableList()
        }else {
            safeApiCall(
                call = { api.getAlbumsList(userId).await() },
                error = "Error fetching user"
            )?.toMutableList().also {
                insertAlbumsToDb(it!!)
            }
        }
    }

     private fun insertAlbumsToDb(albumsList: MutableList<AlbumResponse>){
        albumDao.insertAlbums(albumsList)
    }
}