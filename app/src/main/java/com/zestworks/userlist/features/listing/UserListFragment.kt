package com.zestworks.userlist.features.listing

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.zestworks.userlist.R


class UserListFragment : Fragment() {

    private lateinit var viewModel: UserListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!, UserListViewModelFactory(activity!!))
            .get(UserListViewModel::class.java)

    }

    override fun onStart() {
        super.onStart()

        viewModel.userListState.observe(this, Observer {
            if (it != null) {
                Log.d("", it.toString())
            }
        })
        viewModel.onUILoaded()
    }
}