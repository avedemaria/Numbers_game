package com.example.numbercomposition.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.numbercomposition.R
import com.example.numbercomposition.databinding.ChooseLevelFragmentBinding
import com.example.numbercomposition.databinding.FragmentWelcomeBinding
import com.example.numbercomposition.domain.entity.Level
import com.example.numbercomposition.presentation.GameProcessFragment.Companion.KEY_LEVEL

class ChooseLevelFragment : Fragment() {

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


        with(binding) {

            buttonLevelTest.setOnClickListener {
                launchGameProcessFragment(Level.TEST)
            }
            buttonLevelEasy.setOnClickListener {
                launchGameProcessFragment(Level.EASY)
            }
            buttonLevelNormal.setOnClickListener {
                launchGameProcessFragment(Level.MEDIUM)
            }
            buttonLevelHard.setOnClickListener {
                launchGameProcessFragment(Level.HARD)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {

        const val NAME = "ChooseLevelFragment"

        fun newInstance(): ChooseLevelFragment {
            return ChooseLevelFragment()
        }
    }

    private fun launchGameProcessFragment(level: Level) {

        val args = Bundle().apply {
            putParcelable(KEY_LEVEL, level)
        }
        findNavController().navigate(R.id.action_chooseLevelFragment_to_gameProcessFragment, args)
    }

}