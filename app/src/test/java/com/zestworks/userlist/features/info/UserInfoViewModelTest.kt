package com.zestworks.userlist.features.info

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.kotlintest.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
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
        val userId = 0
        userInfoViewModel = UserInfoViewModel(userInfoRepository, userId)
        every { userInfoRepository.getUserInfo(userInfoViewModel.viewModelScope, userId) }.returns(
            dataSource
        )
    }

    @Test
    fun `Test if ui renders data obtained from the repository`() {
        userInfoViewModel.onUILoad()
        userInfoViewModel.userInfo?.value shouldBe null
        dataSource.postValue(USER_INFO_0)
        userInfoViewModel.userInfo?.value shouldBe USER_INFO_0
    }

    @Test
    fun `Test whether network call is not made again after device rotation`() {
        userInfoViewModel.onUILoad()
        userInfoViewModel.onUILoad()
        userInfoViewModel.onUILoad()
        verify(exactly = 1) {
            userInfoRepository.getUserInfo(userInfoViewModel.viewModelScope, 0)
        }
    }
}