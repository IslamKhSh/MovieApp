package com.github.islamkhsh.movie_app.data.network.entities

import okhttp3.Headers
import okhttp3.ResponseBody


/**
 * Common class used by API responses.
 * @param <T> the type of the responseBody
 **/
class ApiResponse<T> {

    // if request throws exception and can't be send
    var exception: Throwable? = null
    var isCanceled: Boolean = false

    // if request success and receive a pagingApiResponse
    var isResponseSuccessful: Boolean = false // state of pagingApiResponse
    var responseCode: Int? = 0 // pagingApiResponse code
    var responseHeader: Headers? = null // pagingApiResponse headers

    // pagingApiResponse body in case of pagingApiResponse succeeded (T: the POJO class of the pagingApiResponse)
    var responseBody: T? = null

    // pagingApiResponse body in case of pagingApiResponse failed
    var errorBody: ResponseBody? = null
}