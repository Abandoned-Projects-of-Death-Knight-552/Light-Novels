package com.knightshrestha.lightnovels.remotedatabase

import com.knightshrestha.lightnovels.remotedatabase.dataclass.NeonResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface MyAPI {
    @GET("api/rest/fetchall")
    suspend fun fetchAllData() : NeonResponse
}