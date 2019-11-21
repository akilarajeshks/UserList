package com.zestworks.userlist.features.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

class UserInfoViewModel(private val userInfoRepository: UserInfoRepository) : ViewModel() {
    lateinit var userInfo: LiveData<UserInfo>

    fun getUserInfo(userId: Int) {
        userInfo = userInfoRepository.getUserInfo(viewModelScope, userId)
    }
}
