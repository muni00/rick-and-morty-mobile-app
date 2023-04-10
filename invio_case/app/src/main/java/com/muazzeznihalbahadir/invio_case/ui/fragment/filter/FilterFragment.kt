package com.muazzeznihalbahadir.invio_case.ui.fragment.filter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.muazzeznihalbahadir.invio_case.R
import com.muazzeznihalbahadir.invio_case.databinding.FragmentFilterBinding

class FilterFragment : Fragment() {

    private var _binding : FragmentFilterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}