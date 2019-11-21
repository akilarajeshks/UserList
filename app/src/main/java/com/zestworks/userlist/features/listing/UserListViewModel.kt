package com.zestworks.userlist.features.listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

class UserListViewModel(private val userListRepository: UserListRepository) : ViewModel() {
    lateinit var userListState: LiveData<List<User>>

    fun onUILoaded() {
        userListState = userListRepository.getUserList(viewModelScope)
    }
}
