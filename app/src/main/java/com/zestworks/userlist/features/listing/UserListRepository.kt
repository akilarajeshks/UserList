package com.zestworks.userlist.features.listing

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope

interface UserListRepository {
    fun getUserList(coroutineScope: CoroutineScope): LiveData<List<User>>
}