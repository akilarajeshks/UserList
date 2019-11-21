package com.zestworks.userlist.features.info

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zestworks.userlist.features.listing.OfflineFirstUserListRepository
import com.zestworks.userlist.features.listing.UserListViewModel
import com.zestworks.userlist.storage.database.UsersDatabase
import com.zestworks.userlist.storage.network.NetworkService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Suppress("UNCHECKED_CAST")
class UserInfoViewModelFactory(private val context: Context) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val userDAO = UsersDatabase.getDatabase(context).usersDAO()
        val baseURL = " https://n161.tech/api/dummyapi/"
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val networkService = retrofit.create(NetworkService::class.java)
        return UserListViewModel(OfflineFirstUserListRepository(userDAO, networkService)) as T
    }
}