package com.farouk.exomindproject.data.repository

import com.farouk.exomindproject.ExomindApplication
import com.farouk.exomindproject.data.dao.AlbumDao
import com.farouk.exomindproject.data.dao.PhotoDao
import com.farouk.exomindproject.data.database.AppDataBase
import com.farouk.exomindproject.data.model.AlbumResponse
import com.farouk.exomindproject.data.model.PhotoResponse
import com.farouk.exomindproject.data.model.user.UserResponse
import com.farouk.exomindproject.data.remoteApi.ApiInterface

class PhotosRepository(private val api: ApiInterface) : BaseRepository() {

    private var photoDao: PhotoDao = AppDataBase.invoke(ExomindApplication.instance!!).getPhotosDao()

    //get latest news using safe api call
    suspend fun getPhotosByAlbum(albumId:String): MutableList<PhotoResponse>? {
        var dbPhotoAlbum=photoDao.getPhotosByAlbum(albumId)
        return if(!dbPhotoAlbum.isNullOrEmpty()){
            dbPhotoAlbum!!.toMutableList()
        }else {
            safeApiCall(
                call = { api.getPhotosList(albumId).await() },
                error = "Error fetching user"
            )?.toMutableList().also {
                insertPhotosToDb(it!!)
            }
        }
    }

     private fun insertPhotosToDb(albumsList: MutableList<PhotoResponse>){
         photoDao.insertPhotos(albumsList)
    }
}