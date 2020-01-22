package com.farouk.exomindtest.data.repository

import com.farouk.exomindtest.ExomindApplication
import com.farouk.exomindtest.data.dao.AlbumDao
import com.farouk.exomindtest.data.database.AppDataBase
import com.farouk.exomindtest.data.model.AlbumResponse
import com.farouk.exomindtest.data.remoteApi.ApiInterface

class AlbumsRepository(private val api: ApiInterface) : BaseRepository() {

    private var albumDao: AlbumDao = AppDataBase.invoke(ExomindApplication.instance!!).getAlbumsDao()
    //get saved list of user from db or from using api call
    suspend fun getAlbumsByUser(userId:String): MutableList<AlbumResponse>? {
        var dbUserAlbum=albumDao.getAlbumByUser(userId)
        return if(!dbUserAlbum.isNullOrEmpty()){
            dbUserAlbum.toMutableList()
        }else {
            safeApiCall(
                call = { api.getAlbumsList(userId).await() },
                error = "Error fetching user"
            )?.toMutableList().also {
                // insert to db if album is empty
                insertAlbumsToDb(it!!)
            }
        }
    }

     private fun insertAlbumsToDb(albumsList: MutableList<AlbumResponse>){
        albumDao.insertAlbums(albumsList)
    }
}