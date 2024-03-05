package com.lekoal.astonintensiv4.part_2.ui

import android.os.Bundle
import android.view.View
import coil.load
import com.lekoal.astonintensiv4.R
import com.lekoal.astonintensiv4.databinding.FragmentUserEditBinding
import com.lekoal.astonintensiv4.part_2.data.UserInfo
import com.lekoal.astonintensiv4.utils.ViewBindingBaseFragment

class UserEditFragment :
    ViewBindingBaseFragment<FragmentUserEditBinding>(FragmentUserEditBinding::inflate) {

    private var id = 0
    private var name = ""
    private var surname = ""
    private var phone = ""
    private var imageLink = ""
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editArguments = requireArguments()
        if (!editArguments.isEmpty) {
            id = editArguments.getInt(USER_ID, 0)
            name = editArguments.getString(USER_NAME, "")
            surname = editArguments.getString(USER_SURNAME, "")
            phone = editArguments.getString(USER_PHONE, "")
            imageLink = editArguments.getString(USER_IMAGE, "")
        }

        bindUserData()
    }

    companion object {
        const val TAG = "USER_EDIT_FRAGMENT_TAG"
        const val USER_ID = "USER_ID"
        const val USER_NAME = "USER_NAME"
        const val USER_SURNAME = "USER_SURNAME"
        const val USER_PHONE = "USER_PHONE"
        const val USER_IMAGE = "USER_IMAGE"

        @JvmStatic
        fun newInstance(userInfo: UserInfo) =
            UserEditFragment().apply {
                arguments = Bundle().apply {
                    putInt(USER_ID, userInfo.id)
                    putString(USER_NAME, userInfo.name)
                    putString(USER_SURNAME, userInfo.surname)
                    putString(USER_PHONE, userInfo.phone)
                    putString(USER_IMAGE, userInfo.imageLink)
                }
            }
    }

    private fun bindUserData() {
        binding.editNameEditText.setText(name)
        binding.editSurnameEditText.setText(surname)
        binding.editPhoneEditText.setText(phone)
        binding.userEditImageView.load(imageLink) {
            crossfade(enable = true)
            crossfade(100)
            placeholder(R.drawable.image_placeholder)
        }
    }
}