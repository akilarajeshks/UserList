package com.zestworks.userlist.features.listing

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.zestworks.userlist.R
import kotlinx.android.synthetic.main.user_list_fragment.*


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
        viewModel.onUILoaded()

        viewModel.userListState.observe(this, Observer {
            if (it != null) {
                user_list_recycler.visibility = View.VISIBLE
                list_loader.visibility = View.GONE
                if (user_list_recycler.adapter == null) {
                    user_list_recycler.apply {
                        adapter = UserListAdapter(it, object : AdapterCallback {
                            override fun onUserItemClicked(userId: Int) {
                                val actionUserListFragmentToUserInfoFragment =
                                    UserListFragmentDirections.actionUserListFragmentToUserInfoFragment(
                                        userId
                                    )
                                findNavController().navigate(
                                    actionUserListFragmentToUserInfoFragment
                                )
                            }

                        })
                        layoutManager = LinearLayoutManager(this.context)
                    }
                } else {
                    (user_list_recycler.adapter as UserListAdapter).notifyDataSetChanged()
                }
            } else {
                user_list_recycler.visibility = View.GONE
                list_loader.visibility = View.VISIBLE
                ObjectAnimator.ofFloat(list_loader, "rotation", 180f, 0f).apply {
                    duration = 2000
                    repeatCount = ObjectAnimator.INFINITE
                    repeatMode = ObjectAnimator.REVERSE
                    start()
                }
            }
        })
    }
}

interface AdapterCallback {
    fun onUserItemClicked(userId: Int)
}