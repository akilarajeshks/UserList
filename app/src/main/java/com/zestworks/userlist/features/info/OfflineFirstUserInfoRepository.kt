package com.zestworks.userlist.features.info

import androidx.lifecycle.LiveData
import com.zestworks.userlist.storage.database.UsersDAO
import com.zestworks.userlist.storage.network.NetworkService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class OfflineFirstUserInfoRepository(
    val usersDAO: UsersDAO,
    private val networkService: NetworkService
) :
    UserInfoRepository {
    override fun getUserInfo(coroutineScope: CoroutineScope, userId: Int): LiveData<UserInfo> {
        coroutineScope.launch {
            val userInfoResponse = networkService.getUserInfo(userId)
            if (userInfoResponse.isSuccessful && userInfoResponse.body() != null) {
                usersDAO.addUserInfo(userInfoResponse.body()!!)
            }
        }
        return usersDAO.getUserInfo(userId)
    }

}