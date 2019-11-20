package com.zestworks.userlist.listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class UserListViewModel(userListRepository: UserListRepository) : ViewModel() {
    val userListState: LiveData<List<User>> = userListRepository.getUserList()
}
