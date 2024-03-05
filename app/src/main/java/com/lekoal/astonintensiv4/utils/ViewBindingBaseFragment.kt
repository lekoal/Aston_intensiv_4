package com.lekoal.astonintensiv4.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class ViewBindingBaseFragment<T : ViewBinding>(
    private val viewBindingInflater: (
        inflater: LayoutInflater,
        parent: ViewGroup?,
        attachToParent: Boolean
    ) -> T
) : Fragment() {
    private var _binding: T? = null
    val binding get() = requireNotNull(_binding)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = viewBindingInflater(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}