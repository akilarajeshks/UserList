package com.zestworks.userlist.features.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import com.zestworks.userlist.R
import com.zestworks.userlist.storage.database.UsersDAO
import com.zestworks.userlist.storage.network.NetworkService
import kotlinx.android.synthetic.main.user_info_fragment.*
import org.koin.android.ext.android.get
import java.util.*


class UserInfoFragment : Fragment() {

    private lateinit var viewModel: UserInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_info_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (arguments != null) {
            val userId = UserInfoFragmentArgs.fromBundle(arguments!!).userId
            val usersDAO: UsersDAO = get()
            val networkService: NetworkService = get()
            viewModel =
                ViewModelProviders.of(
                    activity!!,
                    UserInfoViewModelFactory(usersDAO, networkService, userId)
                )
                    .get(UserInfoViewModel::class.java)
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.onUILoad()

        viewModel.userInfo?.observe(this, Observer {
            if (it != null) {
                loader.visibility = View.GONE
                renderUserInfo(it)
                content.visibility = View.VISIBLE
            } else {
                content.visibility = View.GONE
                loader.visibility = View.VISIBLE
                loader.startShimmer()
            }
        })
    }

    private fun renderUserInfo(userInfo: UserInfo) {
        Picasso.get().load(userInfo.image).into(user_image)
        user_name.text = String.format(
            getString(R.string.username),
            userInfo.nameTitle.toUpperCase(Locale.getDefault()),
            userInfo.firstName,
            userInfo.lastName
        )
        user_gender.text = String.format(getString(R.string.gender), userInfo.gender)
        user_email.text = String.format(getString(R.string.email), userInfo.email)
        user_dob.text = String.format(getString(R.string.dob), userInfo.dob)
        user_phone.text = String.format(getString(R.string.phone), userInfo.phone)
        user_cell.text = String.format(getString(R.string.cell), userInfo.cell)
    }
}
