package com.zestworks.userlist.features.info

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.kotlintest.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserInfoViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val userInfoRepository = mockk<UserInfoRepository>()
    private val dataSource = MutableLiveData<UserInfo>()
    private lateinit var userInfoViewModel: UserInfoViewModel

    @Before
    fun setUp() {
        userInfoViewModel = UserInfoViewModel(userInfoRepository)
        every { userInfoRepository.getUserInfo(userInfoViewModel.viewModelScope, 0) }.returns(
            dataSource
        )
    }

    @Test
    fun `Test if ui renders data obtained from the repository`() {
        userInfoViewModel.getUserInfo(0)
        userInfoViewModel.userInfo.value shouldBe null
        dataSource.postValue(USER_INFO_0)
        userInfoViewModel.userInfo.value shouldBe USER_INFO_0
    }
}