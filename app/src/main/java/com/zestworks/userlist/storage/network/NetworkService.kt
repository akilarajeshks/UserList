package com.zestworks.userlist.storage.network

import com.zestworks.userlist.features.listing.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface NetworkService {
    @GET("user")
    suspend fun getUsersList(): Response<UserResponse>
}