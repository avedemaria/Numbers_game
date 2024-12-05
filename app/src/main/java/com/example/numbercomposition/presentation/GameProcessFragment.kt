package com.example.numbercomposition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.numbercomposition.R
import com.example.numbercomposition.databinding.FragmentGameProcessBinding
import com.example.numbercomposition.databinding.FragmentWelcomeBinding

class GameProcessFragment: Fragment() {

    private var _binding: FragmentGameProcessBinding? = null
    private val binding: FragmentGameProcessBinding
        get() = _binding ?: throw RuntimeException("FragmentWelcomeBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameProcessBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvOption1
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}