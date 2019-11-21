package com.zestworks.userlist.features.listing

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class UserResponse(
    @SerializedName("data")
    val users: List<User>
)

@Entity(tableName = "user_table")
data class User(
    @SerializedName("firstName")
    val firstName: String,
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("nameTitle")
    val nameTitle: String
)