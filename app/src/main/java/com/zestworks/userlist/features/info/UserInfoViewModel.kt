package com.zestworks.userlist.features.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

class UserInfoViewModel(
    private val userInfoRepository: UserInfoRepository,
    private val userId: Int
) : ViewModel() {
    var userInfo: LiveData<UserInfo>? = null

    fun onUILoad() {
        if (userInfo == null || userInfo?.value?.id != userId) {
            userInfo = userInfoRepository.getUserInfo(viewModelScope, userId)
        }
    }
}
