package com.farouk.exomindtest.data.remoteApi

import com.farouk.exomindtest.ExomindApplication
import com.farouk.exomindtest.data.AppUtils
import com.farouk.exomindtest.data.Constants
import java.io.IOException

import okhttp3.Interceptor
import okhttp3.Response

class EndpointInterceptor :
    Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        var response: Response? = null

        val url = request.url().toString()
        if (url.contains(Constants.BASE_URL)) {
            if (!AppUtils.isNetworkAvailable(ExomindApplication.instance)) {
                throw IOException("No network available")
            }
        }
        response=chain.proceed(request)

        return response
    }

    companion object {

        private val TAG = EndpointInterceptor::class.java.simpleName
    }
}
