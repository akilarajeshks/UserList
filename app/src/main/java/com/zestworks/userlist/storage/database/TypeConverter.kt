package com.zestworks.userlist.storage.database

import android.text.TextUtils
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.zestworks.userlist.features.info.Location

class TypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun stringToLocation(string: String): Location? {
        if (TextUtils.isEmpty(string))
            return null
        return gson.fromJson(string, Location::class.java)
    }

    @TypeConverter
    fun locationToString(outboxItem: Location): String {
        return gson.toJson(outboxItem)
    }
}