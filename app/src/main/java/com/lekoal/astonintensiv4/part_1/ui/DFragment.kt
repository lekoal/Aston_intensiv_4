package com.lekoal.astonintensiv4.part_1.ui

import android.os.Bundle
import com.lekoal.astonintensiv4.databinding.FragmentDBinding
import com.lekoal.astonintensiv4.utils.ViewBindingBaseFragment

class DFragment : ViewBindingBaseFragment<FragmentDBinding>(FragmentDBinding::inflate) {
    companion object {
        const val TAG = "D_FRAGMENT_TAG"
        @JvmStatic
        fun newInstance() =
            DFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}