package com.exa.android.vlogassignment.data.remote.api

import com.exa.android.vlogassignment.data.remote.dto.VlogDto
import com.exa.android.vlogassignment.data.remote.dto.VlogItems
import retrofit2.http.GET
import retrofit2.http.Query

interface VlogApi {
    @GET("wp-json/wp/v2/posts")
    suspend fun getVlogs(
        @Query("per_page")pageCount : Int,
        @Query("page") page: Int
    ): List<VlogDto>

    companion object{
        const val BASE_URL = "https://blog.vrid.in/"
    }
}

