package com.zestworks.userlist.features.listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

class UserListViewModel(private val userListRepository: UserListRepository) : ViewModel() {
    var userListState: LiveData<List<User>>? = null

    fun onUILoaded() {
        if (userListState == null) {
            userListState = userListRepository.getUserList(viewModelScope)
        }
    }
}
