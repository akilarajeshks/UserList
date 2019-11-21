package com.zestworks.userlist.features.listing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zestworks.userlist.storage.database.UsersDAO
import com.zestworks.userlist.storage.network.NetworkService

@Suppress("UNCHECKED_CAST")
class UserListViewModelFactory(
    private val userDAO: UsersDAO,
    private val networkService: NetworkService
) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return UserListViewModel(OfflineFirstUserListRepository(userDAO, networkService)) as T
    }
}