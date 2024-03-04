package com.lekoal.astonintensiv4.part_1.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import com.lekoal.astonintensiv4.databinding.FragmentDBinding
import com.lekoal.astonintensiv4.utils.ViewBindingBaseFragment

class DFragment : ViewBindingBaseFragment<FragmentDBinding>(FragmentDBinding::inflate) {
    companion object {
        const val TAG = "D_FRAGMENT_TAG"
        @JvmStatic
        fun newInstance() = DFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setButtonsListener()
    }

    private fun setButtonsListener() {
        childFragmentManager.popBackStack(BFragment.TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}