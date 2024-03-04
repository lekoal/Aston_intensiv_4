package com.lekoal.astonintensiv4.part_1.ui

import com.lekoal.astonintensiv4.databinding.FragmentBBinding
import com.lekoal.astonintensiv4.utils.ViewBindingBaseFragment

class BFragment : ViewBindingBaseFragment<FragmentBBinding>(FragmentBBinding::inflate) {
    companion object {
        const val TAG = "B_FRAGMENT_TAG"
        @JvmStatic
        fun newInstance() = BFragment()
    }
}