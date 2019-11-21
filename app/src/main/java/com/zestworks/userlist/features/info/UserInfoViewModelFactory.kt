package com.zestworks.userlist.features.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zestworks.userlist.storage.database.UsersDAO
import com.zestworks.userlist.storage.network.NetworkService

@Suppress("UNCHECKED_CAST")
class UserInfoViewModelFactory(
    private val userDAO: UsersDAO,
    private val networkService: NetworkService,
    private val userId: Int
) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserInfoViewModel(
            OfflineFirstUserInfoRepository(userDAO, networkService),
            userId
        ) as T
    }
}