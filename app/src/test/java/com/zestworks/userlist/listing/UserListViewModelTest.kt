package com.zestworks.userlist.listing

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
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
        every { userListRepository.getUserList() }.returns(dataSource)
        userListViewModel = UserListViewModel(userListRepository)
    }

    @Test
    fun `Test if ui renders data obtained from the repository`() {
        userListViewModel.userListState.value shouldBe null
        dataSource.postValue(DUMMY_USERS)
        userListViewModel.userListState.value shouldBe DUMMY_USERS
    }

}