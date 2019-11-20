package com.zestworks.userlist.listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class OfflineFirstUserListRepository : UserListRepository {
    override fun getUserList(): LiveData<List<User>> {
        return MutableLiveData<List<User>>()
    }
}