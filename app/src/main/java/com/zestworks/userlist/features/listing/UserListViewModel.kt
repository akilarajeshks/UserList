package com.zestworks.userlist.features.listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

class UserListViewModel(private val userListRepository: UserListRepository) : ViewModel() {
    private val _userListState = MutableLiveData<List<User>>()
    val userListState: LiveData<List<User>> = _userListState

    fun onUILoaded() {
        _userListState.postValue(userListRepository.getUserList(viewModelScope).value)
    }
}
