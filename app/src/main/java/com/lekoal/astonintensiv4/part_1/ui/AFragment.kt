package com.lekoal.astonintensiv4.part_1.ui

import android.os.Bundle
import android.view.View
import com.lekoal.astonintensiv4.databinding.FragmentABinding
import com.lekoal.astonintensiv4.utils.ViewBindingBaseFragment

class AFragment : ViewBindingBaseFragment<FragmentABinding>(FragmentABinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setButtonsListener()
    }
    companion object {
        const val TAG = "A_FRAGMENT_TAG"
        @JvmStatic
        fun newInstance() = AFragment()
    }

    private fun setButtonsListener() {
        binding.goToBButton.setOnClickListener {
            if (childFragmentManager.findFragmentByTag(BFragment.TAG) == null) {
                val bFragment = BFragment.newInstance()
                childFragmentManager.beginTransaction()
                    .add(binding.aFragmentContainer.id, bFragment, BFragment.TAG)
                    .addToBackStack(BFragment.TAG)
                    .commit()
            }
        }
    }
}