package com.zestworks.userlist.features.info

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user_info_table")
data class UserInfo(
    @SerializedName("cell")
    val cell: String,
    @SerializedName("dob")
    val dob: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    @PrimaryKey
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("location")
    val location: Location,
    @SerializedName("nameTitle")
    val nameTitle: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("username")
    val username: String
)

data class Location(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("city")
    val city: String,
    @SerializedName("postcode")
    val postcode: Int,
    @SerializedName("state")
    val state: String,
    @SerializedName("street")
    val street: String,
    @SerializedName("timezone")
    val timezone: String
)