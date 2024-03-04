package com.lekoal.astonintensiv4.part_1.ui

import android.os.Bundle
import android.view.View
import com.lekoal.astonintensiv4.databinding.FragmentCBinding
import com.lekoal.astonintensiv4.utils.ViewBindingBaseFragment

const val B_TO_C_TEXT = "B_TO_C_TEXT"

class CFragment : ViewBindingBaseFragment<FragmentCBinding>(FragmentCBinding::inflate) {
    companion object {
        const val TAG = "C_FRAGMENT_TAG"

        @JvmStatic
        fun newInstance(text: String) =
            CFragment().apply {
                arguments = Bundle().apply {
                    putString(B_TO_C_TEXT, text)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arguments = requireArguments()
        if (!arguments.isEmpty) {
            val translatedText = arguments.getString(B_TO_C_TEXT, "")
            binding.textFromBTextView.text = translatedText
        }

        setButtonsListeners()
    }

    private fun setButtonsListeners() {
        binding.goToDButton.setOnClickListener {
            if (parentFragmentManager.findFragmentByTag(DFragment.TAG) == null) {
                val dFragment = DFragment.newInstance()
                parentFragmentManager.beginTransaction()
                    .add(binding.cFragmentContainer.id, dFragment, DFragment.TAG)
                    .addToBackStack(DFragment.TAG)
                    .commit()
            }
        }
        binding.goBackToAButton.setOnClickListener {
            parentFragmentManager.popBackStack(
                AFragment.TAG,
                0
            )
        }
    }
}