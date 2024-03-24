package com.lekoal.astonintensiv4.part_2.ui

import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.setFragmentResultListener
import coil.load
import com.lekoal.astonintensiv4.R
import com.lekoal.astonintensiv4.databinding.FragmentUserListBinding
import com.lekoal.astonintensiv4.databinding.UserListItemBinding
import com.lekoal.astonintensiv4.part_2.data.InitialUsers
import com.lekoal.astonintensiv4.part_2.data.UserInfo
import com.lekoal.astonintensiv4.utils.ViewBindingBaseFragment
import java.io.Serializable

const val RESULT_LISTENER_KEY = "RESULT_LISTENER_KEY"

class UserListFragment :
    ViewBindingBaseFragment<FragmentUserListBinding>(FragmentUserListBinding::inflate) {
    private var userList = InitialUsers.get()

    companion object {
        const val TAG = "USER_LIST_FRAGMENT"
        private const val SAVED_USER_LIST = "SAVED_USER_LIST"

        @JvmStatic
        fun newInstance() = UserListFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userList = if (savedInstanceState != null) {
            if (VERSION.SDK_INT >= VERSION_CODES.TIRAMISU) {
                savedInstanceState.getSerializable(
                    SAVED_USER_LIST,
                    ArrayList<UserInfo>()::class.java
                )
            } else {
                savedInstanceState.getSerializable(SAVED_USER_LIST) as? List<*>
            }?.filterIsInstance<UserInfo>() ?: listOf()
        } else {
            InitialUsers.get()
        }
        if (userList.isNotEmpty()) {
            setListUserData(userList)
        } else {
            userList = InitialUsers.get()
            setListUserData(userList)
        }

        setEditFragmentListener()
    }

    private fun setListUserData(userList: List<UserInfo>) {
        userList.forEach {
            setUserData(it)
        }
    }

    private fun setUserData(userInfo: UserInfo) {
        when (userInfo.id) {
            1 -> {
                bindUserData(userBinding = binding.userListFirstItem, userInfo = userInfo)
            }

            2 -> {
                bindUserData(userBinding = binding.userListSecondItem, userInfo = userInfo)
            }

            3 -> {
                bindUserData(userBinding = binding.userListThirdItem, userInfo = userInfo)
            }

            4 -> {
                bindUserData(userBinding = binding.userListFourthItem, userInfo = userInfo)
            }
        }
    }

    private fun bindUserData(
        userBinding: UserListItemBinding,
        userInfo: UserInfo
    ) {
        setUserItemListener(userBinding, userInfo)
        setImage(userBinding.userImage, userInfo.imageLink)
        userBinding.userName.text = userInfo.name
        userBinding.userSurname.text = userInfo.surname
        userBinding.userPhone.text = userInfo.phone
    }

    private fun setImage(imageView: AppCompatImageView, imageLink: String) {
        imageView.load(imageLink) {
            crossfade(enable = true)
            crossfade(100)
            placeholder(R.drawable.image_placeholder)
        }
    }

    private fun setUserItemListener(userBinding: UserListItemBinding, userInfo: UserInfo) {
        userBinding.userListItem.setOnClickListener {
            if (parentFragmentManager.findFragmentByTag(UserEditFragment.TAG) == null) {
                val userEditFragment = UserEditFragment.newInstance(userInfo)
                parentFragmentManager.beginTransaction()
                    .addToBackStack(UserEditFragment.TAG)
                    .replace(
                        binding.userListFragmentContainer.id,
                        userEditFragment,
                        UserEditFragment.TAG
                    )
                    .commit()
            }
        }
    }

    private fun setEditFragmentListener() {
        setFragmentResultListener(RESULT_LISTENER_KEY) { _, bundle ->
            val editedUserInfo =
                if (VERSION.SDK_INT >= VERSION_CODES.TIRAMISU) {
                    bundle.getSerializable(
                        UserEditFragment.USER_INFO_DATA,
                        UserInfo::class.java
                    ) as UserInfo
                } else {
                    bundle.getSerializable(UserEditFragment.USER_INFO_DATA) as UserInfo
                }
            userList = userList.map {
                if (it.id == editedUserInfo.id) editedUserInfo else it
            }
            setUserData(editedUserInfo)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(SAVED_USER_LIST, userList as Serializable)
    }
}