package com.github.islamkhsh.movie_app.common.extensions

import androidx.lifecycle.MutableLiveData
import com.github.islamkhsh.movie_app.data.network.entities.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T>Call<T>.getResponse(): MutableLiveData<ApiResponse<T>> {


    val liveDataResponse = MutableLiveData<ApiResponse<T>>()
    val apiResponse = ApiResponse<T>()


    enqueue(object : Callback<T> {

        override fun onFailure(call: Call<T>, t: Throwable) {
            apiResponse.exception = t
            apiResponse.isCanceled = call.isCanceled

            liveDataResponse.postValue(apiResponse)
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            apiResponse.responseCode = response.code()
            apiResponse.isResponseSuccessful = response.isSuccessful
            apiResponse.responseHeader = response.headers()

            if (response.isSuccessful)
                apiResponse.responseBody = response.body()
            else
                apiResponse.errorBody = response.errorBody()

            liveDataResponse.postValue(apiResponse)
        }
    })

    return liveDataResponse
}
