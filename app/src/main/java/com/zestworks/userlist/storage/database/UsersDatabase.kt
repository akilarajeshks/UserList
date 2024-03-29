package com.zestworks.userlist.storage.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.zestworks.userlist.features.info.UserInfo
import com.zestworks.userlist.features.listing.User

@Database(entities = [User::class, UserInfo::class], version = 1)
@TypeConverters(TypeConverter::class)
abstract class UsersDatabase : RoomDatabase() {
    abstract fun usersDAO(): UsersDAO

    companion object {
        private var INSTANCE: UsersDatabase? = null

        fun getDatabase(context: Context): UsersDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UsersDatabase::class.java,
                    "Users_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}