package com.lekoal.astonintensiv4.part_1.ui

import android.os.Bundle
import android.view.View
import com.lekoal.astonintensiv4.R
import com.lekoal.astonintensiv4.databinding.FragmentBBinding
import com.lekoal.astonintensiv4.utils.ViewBindingBaseFragment

class BFragment : ViewBindingBaseFragment<FragmentBBinding>(FragmentBBinding::inflate) {
    companion object {
        const val TAG = "B_FRAGMENT_TAG"
        @JvmStatic
        fun newInstance() = BFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setButtonsListener()
    }

    private fun setButtonsListener() {
        binding.goToCButton.setOnClickListener {
            if (childFragmentManager.findFragmentByTag(CFragment.TAG) == null) {
                val translatedText = getString(R.string.translatex_text_from_b_to_c)
                val cFragment = CFragment.newInstance(translatedText)
                childFragmentManager.beginTransaction()
                    .add(binding.bFragmentContainer.id, cFragment, CFragment.TAG)
                    .addToBackStack(CFragment.TAG)
                    .commit()
            }
        }
        binding.goBackButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}