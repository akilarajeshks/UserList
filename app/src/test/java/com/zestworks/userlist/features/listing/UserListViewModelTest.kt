package com.zestworks.userlist.features.listing

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.kotlintest.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserListViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val userListRepository = mockk<UserListRepository>()
    private val dataSource = MutableLiveData<List<User>>()
    private lateinit var userListViewModel: UserListViewModel

    @Before
    fun setUp() {
        userListViewModel = UserListViewModel(userListRepository)
        every { userListRepository.getUserList(userListViewModel.viewModelScope) }.returns(
            dataSource
        )
    }

    @Test
    fun `Test if ui renders data obtained from the repository`() {
        userListViewModel.onUILoaded()
        userListViewModel.userListState.value shouldBe null
        dataSource.postValue(DUMMY_USERS)
        userListViewModel.userListState.value shouldBe DUMMY_USERS
    }

}