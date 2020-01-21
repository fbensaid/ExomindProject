package com.farouk.exomindproject.data.repository

import android.util.Log
import com.farouk.exomindproject.ExomindApplication
import com.farouk.exomindproject.data.remoteApi.Output
import retrofit2.Response
import java.io.IOException

open class BaseRepository {


    constructor() {
        ExomindApplication.appComponent.inject(baseRepository = this)
    }


    suspend fun <T : Any> safeApiCall(call : suspend()-> Response<T>, error : String) :  T?{
        val result = apiOutput(call, error)
        var output : T? = null
        when(result){
            is Output.Success ->
                output = result.output
            is Output.Error -> Log.e("Error", "The $error and the ${result.exception}")
        }
        return output

    }
    private suspend fun<T : Any> apiOutput(call: suspend()-> Response<T> , error: String) : Output<T>{
        val response = call.invoke()
        return if (response.isSuccessful)
            Output.Success(response.body()!!)
        else
            Output.Error(IOException("OOps .. Something went wrong due to  $error"))
    }
}