package com.lekoal.astonintensiv4.part_2.ui

import com.lekoal.astonintensiv4.databinding.FragmentUserListBinding
import com.lekoal.astonintensiv4.utils.ViewBindingBaseFragment

class UserListFragment :
    ViewBindingBaseFragment<FragmentUserListBinding>(FragmentUserListBinding::inflate) {
    companion object {
        const val TAG = "USER_LIST_FRAGMENT"
        @JvmStatic
        fun newInstance() = UserListFragment()
    }
}