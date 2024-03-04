package com.lekoal.astonintensiv4.part_1.ui

import android.os.Bundle
import com.lekoal.astonintensiv4.databinding.FragmentCBinding
import com.lekoal.astonintensiv4.utils.ViewBindingBaseFragment

class CFragment : ViewBindingBaseFragment<FragmentCBinding>(FragmentCBinding::inflate) {
    companion object {
        const val TAG = "C_FRAGMENT_TAG"
        @JvmStatic
        fun newInstance() =
            CFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}