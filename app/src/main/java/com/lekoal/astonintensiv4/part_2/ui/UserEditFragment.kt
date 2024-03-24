package com.lekoal.astonintensiv4.part_2.ui

import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResult
import coil.load
import com.lekoal.astonintensiv4.R
import com.lekoal.astonintensiv4.databinding.FragmentUserEditBinding
import com.lekoal.astonintensiv4.part_2.data.UserInfo
import com.lekoal.astonintensiv4.utils.ViewBindingBaseFragment

class UserEditFragment :
    ViewBindingBaseFragment<FragmentUserEditBinding>(FragmentUserEditBinding::inflate) {

    private var userId = 0
    private var name = ""
    private var surname = ""
    private var phone = ""
    private var imageLink = ""
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editArguments = requireArguments()
        if (!editArguments.isEmpty) {
            val userInfoData = if (VERSION.SDK_INT >= VERSION_CODES.TIRAMISU) {
                editArguments.getSerializable(USER_INFO_DATA, UserInfo::class.java) as UserInfo
            } else {
                editArguments.getSerializable(USER_INFO_DATA) as UserInfo
            }

            userId = userInfoData.id
            name = userInfoData.name
            surname = userInfoData.surname
            phone = userInfoData.phone
            imageLink = userInfoData.imageLink
        }
        if (savedInstanceState != null) {
            imageLink = savedInstanceState.getString(EDITED_IMAGE_LINK, "")
        }
        bindUserData()
        setButtonsListeners()
    }

    companion object {
        private const val EDITED_IMAGE_LINK = "EDITED_IMAGE_LINK"
        const val TAG = "USER_EDIT_FRAGMENT_TAG"
        const val USER_INFO_DATA = "USER_INFO_DATA"

        @JvmStatic
        fun newInstance(userInfo: UserInfo) =
            UserEditFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(USER_INFO_DATA, userInfo)
                }
            }
    }

    private fun bindUserData() {
        binding.editNameEditText.setText(name)
        binding.editSurnameEditText.setText(surname)
        binding.editPhoneEditText.setText(phone)
        loadUserImage(imageLink)
    }

    private fun setButtonsListeners() {
        binding.editButtonCancel.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding.editImageButtonOk.setOnClickListener {
            if (binding.editImageKeyEditText.text?.isNotEmpty() == true) {
                imageLink = "https://loremflickr.com/150/200/${binding.editImageKeyEditText.text}"
                loadUserImage(imageLink)
            }
        }
        binding.editButtonConfirm.setOnClickListener {
            setNewUserInfo()
            sendResult()
            parentFragmentManager.popBackStack()
        }
    }

    private fun loadUserImage(imageLink: String) {
        binding.userEditImageView.load(imageLink) {
            crossfade(enable = true)
            crossfade(100)
            placeholder(R.drawable.image_placeholder)
        }
    }

    private fun setNewUserInfo() {
        name = binding.editNameEditText.text.toString()
        surname = binding.editSurnameEditText.text.toString()
        phone = binding.editPhoneEditText.text.toString()
    }

    private fun sendResult() {
        val result = Bundle().apply {
            putSerializable(USER_INFO_DATA, UserInfo(
                id = userId,
                name = name,
                surname = surname,
                phone = phone,
                imageLink = imageLink
            ))
        }
        setFragmentResult(RESULT_LISTENER_KEY, result)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(EDITED_IMAGE_LINK, imageLink)
    }
}