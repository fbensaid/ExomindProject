package com.farouk.exomindtest.data.repository

import com.farouk.exomindtest.data.model.user.UserResponse
import com.farouk.exomindtest.data.remoteApi.ApiInterface

class UserRepository(private val api: ApiInterface) : BaseRepository() {

    //get latest news using safe api call
    suspend fun getUsers(): MutableList<UserResponse>? {
        return safeApiCall(
            //await the result of deferred type
            call = { api.getUsersList().await() },
            error = "Error fetching user"
            //convert to mutable list
        )?.toMutableList()
    }
}