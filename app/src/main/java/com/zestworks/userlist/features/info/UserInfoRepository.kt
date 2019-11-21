package com.zestworks.userlist.features.info

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope

interface UserInfoRepository {
    fun getUserInfo(coroutineScope: CoroutineScope, userId: Int): LiveData<UserInfo>
}