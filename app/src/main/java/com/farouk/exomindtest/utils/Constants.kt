package com.farouk.exomindtest.utils


class Constants {

    companion object {
        const val BASE_URL: String = "http://jsonplaceholder.typicode.com/"
        const val USER_ID: String = "userId"
        const val ALBUM_ID: String = "albumId"


    }

    object EndPoints {
        const val USER_ENDPOINT  = "users"
        const val ALBUM_ENDPOINT  = "users/1/albums"
        const val PHOTO_ENDPOINT  = "users/1/photos"
    }


}
