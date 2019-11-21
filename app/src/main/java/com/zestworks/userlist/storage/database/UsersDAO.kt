package com.zestworks.userlist.storage.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.zestworks.userlist.features.listing.User

@Dao
interface UsersDAO {

    @Query("SELECT * from user_table")
    fun getAllUsers(): LiveData<List<User>>

    @Insert(onConflict = REPLACE)
    suspend fun addAllUsers(usersList: List<User>)


}