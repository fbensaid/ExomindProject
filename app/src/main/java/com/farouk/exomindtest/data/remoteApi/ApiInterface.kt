package com.farouk.exomindtest.data.remoteApi

import com.farouk.exomindtest.data.Constants
import com.farouk.exomindtest.data.model.AlbumResponse
import com.farouk.exomindtest.data.model.PhotoResponse
import com.farouk.exomindtest.data.model.user.UserResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET(Constants.EndPoints.USER_ENDPOINT)
    fun getUsersList(): Deferred<Response<List<UserResponse>>>

    @GET(Constants.EndPoints.ALBUM_ENDPOINT)
    fun getAlbumsList(@Query("userId")userId:String): Deferred<Response<List<AlbumResponse>>>


    @GET(Constants.EndPoints.PHOTO_ENDPOINT)
    fun getPhotosList(@Query("albumId")albumId:String): Deferred<Response<List<PhotoResponse>>>

}