package com.zestworks.userlist.features.listing

import android.util.Log
import androidx.lifecycle.LiveData
import com.zestworks.userlist.storage.database.UsersDAO
import com.zestworks.userlist.storage.network.NetworkService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class OfflineFirstUserListRepository(
    private val usersDAO: UsersDAO,
    private val networkService: NetworkService
) :
    UserListRepository {
    override fun getUserList(coroutineScope: CoroutineScope): LiveData<List<User>> {
        coroutineScope.launch {
            try {
                val usersListResponse = networkService.getUsersList()
                if (usersListResponse.isSuccessful && usersListResponse.body() != null) {
                    usersDAO.addAllUsers(usersListResponse.body()!!.users)
                }
            } catch (exception: Exception) {
                Log.d("Network exception", exception.toString())
            }
        }
        return usersDAO.getAllUsers()
    }
}