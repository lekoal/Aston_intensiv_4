package com.lekoal.astonintensiv4.part_2.ui

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

const val RESULT_LISTENER_KEY = "RESULT_LISTENER_KEY"

class UserListFragment :
    ViewBindingBaseFragment<FragmentUserListBinding>(FragmentUserListBinding::inflate) {
    companion object {
        const val TAG = "USER_LIST_FRAGMENT"

        @JvmStatic
        fun newInstance() = UserListFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val initialUserList = InitialUsers.get()

        setListUserData(initialUserList)
        setEditFragmentListener()
    }

    private fun setListUserData(userList: List<UserInfo>) {
        userList.forEach {
            setUserData(it.id, it)
        }
    }

    private fun setUserData(id: Int, userInfo: UserInfo) {
        when (id) {
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
            val userEditFragment = UserEditFragment.newInstance(userInfo)
            if (parentFragmentManager.findFragmentByTag(UserEditFragment.TAG) == null) {
                parentFragmentManager.beginTransaction()
                    .addToBackStack(UserEditFragment.TAG)
                    .add(binding.userListLayout.id, userEditFragment, UserEditFragment.TAG)
                    .commit()
            }
        }
    }

    private fun setEditFragmentListener() {
        setFragmentResultListener(RESULT_LISTENER_KEY) { _, bundle ->
            val editedId = bundle.getInt(UserEditFragment.USER_ID)
            val editedName = bundle.getString(UserEditFragment.USER_NAME)
            val editedSurname = bundle.getString(UserEditFragment.USER_SURNAME)
            val editedPhone = bundle.getString(UserEditFragment.USER_PHONE)
            val editedImageLink = bundle.getString(UserEditFragment.USER_IMAGE)

            val editedUserInfo = UserInfo(
                id = editedId,
                name = editedName.toString(),
                surname = editedSurname.toString(),
                phone = editedPhone.toString(),
                imageLink = editedImageLink.toString()
            )
            setUserData(editedUserInfo.id, editedUserInfo)
        }
    }
}