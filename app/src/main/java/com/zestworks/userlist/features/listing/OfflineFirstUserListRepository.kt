package com.zestworks.userlist.features.listing

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
            val usersListResponse = networkService.getUsersList()
            if (usersListResponse.isSuccessful) {
                if (usersListResponse.body() != null) {
                    usersDAO.addAllUsers(usersListResponse.body()!!.users)
                }
            }
        }
        return usersDAO.getAllUsers()
    }
}