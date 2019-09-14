package com.github.islamkhsh.movie_app.data.network.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.github.islamkhsh.movie_app.data.network.entities.ApiResponse
import com.github.islamkhsh.movie_app.data.network.entities.Movie
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PagingDataSource(val call: (Int)-> Call<Movie>) : PageKeyedDataSource<Int, Movie.Result>() {

    val pagingApiResponse = MutableLiveData<ApiResponse<ResponseBody>>()
    val apiResponse = ApiResponse<ResponseBody>()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie.Result>
    ) {
        call(1).enqueue(object : Callback<Movie>{
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                apiResponse.exception = t
                apiResponse.isCanceled = call.isCanceled

                pagingApiResponse.value = apiResponse
            }

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {

                apiResponse.responseCode = response.code()
                apiResponse.isResponseSuccessful = response.isSuccessful

                if (response.isSuccessful)
                    callback.onResult(response.body()!!.results, null, 2)
                else
                    apiResponse.errorBody = response.errorBody()

                pagingApiResponse.value = apiResponse
            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie.Result>) {

        call(params.key).enqueue(object : Callback<Movie>{
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                apiResponse.exception = t
                apiResponse.isCanceled = call.isCanceled

                pagingApiResponse.value = apiResponse
            }

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {

                apiResponse.responseCode = response.code()
                apiResponse.isResponseSuccessful = response.isSuccessful

                if (response.isSuccessful){
                    val nextPage = if (response.body()!!.totalPages == params.key) null
                                        else params.key + 1

                    callback.onResult(response.body()!!.results, nextPage)
                } else
                    apiResponse.errorBody = response.errorBody()

                pagingApiResponse.value = apiResponse
            }
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie.Result>) {}
}