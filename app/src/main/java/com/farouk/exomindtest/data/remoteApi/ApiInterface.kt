package com.farouk.exomindtest.data.remoteApi

import com.farouk.exomindtest.utils.Constants
import com.farouk.exomindtest.data.model.AlbumResponse
import com.farouk.exomindtest.data.model.PhotoResponse
import com.farouk.exomindtest.data.model.user.UserResponse
import com.farouk.exomindtest.utils.Constants.Companion.ALBUM_ID
import com.farouk.exomindtest.utils.Constants.Companion.USER_ID
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET(Constants.EndPoints.USER_ENDPOINT)
    fun getUsersList(): Deferred<Response<List<UserResponse>>>

    @GET(Constants.EndPoints.ALBUM_ENDPOINT)
    fun getAlbumsList(@Query(USER_ID)userId:String): Deferred<Response<List<AlbumResponse>>>


    @GET(Constants.EndPoints.PHOTO_ENDPOINT)
    fun getPhotosList(@Query(ALBUM_ID)albumId:String): Deferred<Response<List<PhotoResponse>>>

}