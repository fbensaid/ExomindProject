package com.farouk.exomindtest.data.repository

import com.farouk.exomindtest.ExomindApplication
import com.farouk.exomindtest.data.dao.PhotoDao
import com.farouk.exomindtest.data.database.AppDataBase
import com.farouk.exomindtest.data.model.PhotoResponse
import com.farouk.exomindtest.data.remoteApi.ApiInterface

class PhotosRepository(private val api: ApiInterface) : BaseRepository() {

    private var photoDao: PhotoDao = AppDataBase.invoke(ExomindApplication.instance!!).getPhotosDao()

    //get latest photo from DB or from safe api call
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