package com.example.numbercomposition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.numbercomposition.R
import com.example.numbercomposition.databinding.ChooseLevelFragmentBinding
import com.example.numbercomposition.databinding.FragmentWelcomeBinding

class ChooseLevelFragment: Fragment() {

    private var _binding: ChooseLevelFragmentBinding? = null
    private val binding: ChooseLevelFragmentBinding
        get() = _binding ?: throw RuntimeException("СhooseLevelFragmentBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ChooseLevelFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonLevelEasy
        binding.buttonLevelNormal
        binding.buttonLevelHard
        binding.buttonLevelTest
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}