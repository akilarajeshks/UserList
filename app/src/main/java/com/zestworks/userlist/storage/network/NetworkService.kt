package com.zestworks.userlist.storage.network

import com.zestworks.userlist.features.info.UserInfo
import com.zestworks.userlist.features.listing.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkService {
    @GET("user")
    suspend fun getUsersList(): Response<UserResponse>

    @GET("user/{userId}")
    suspend fun getUserInfo(@Path("userId") userId: Int): Response<UserInfo>
}