package com.zestworks.userlist.listing

import com.google.gson.annotations.SerializedName

data class Users(
    @SerializedName("data")
    val users: List<User>
)

data class User(
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("nameTitle")
    val nameTitle: String
)