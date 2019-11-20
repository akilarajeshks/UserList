package com.zestworks.userlist.listing

import androidx.lifecycle.LiveData

interface UserListRepository {
    fun getUserList(): LiveData<List<User>>
}