package com.example.numbercomposition.presentation

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.numbercomposition.R
import com.example.numbercomposition.databinding.FragmentGameProcessBinding
import com.example.numbercomposition.domain.entity.GameResult
import com.example.numbercomposition.domain.entity.GameSettings
import com.example.numbercomposition.domain.entity.Level

@Suppress("DEPRECATION")
class GameProcessFragment : Fragment() {


    private lateinit var level: Level



    private var _binding: FragmentGameProcessBinding? = null
    private val binding: FragmentGameProcessBinding
        get() = _binding ?: throw RuntimeException("FragmentWelcomeBinding is null")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

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
        binding.tvOption1.setOnClickListener{
            launchGameFinishedFragment(GameResult(false, 0,
                0, GameSettings(0,
                    0,0,10)
            ))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseArgs() {
        level = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getParcelable(KEY_LEVEL, Level::class.java)
        } else {
            requireArguments().getParcelable(KEY_LEVEL) as? Level
        } ?: throw RuntimeException("Level is null")
    }


    private fun launchGameFinishedFragment (gameResult: GameResult) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFinishedFragment.newInstance(gameResult))
            .addToBackStack(null)
            .commit()
    }

    companion object {

       const val NAME = "GameProcessFragment"

        private const val KEY_LEVEL = "level"

        fun newInstance(level: Level): GameProcessFragment {
            return GameProcessFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_LEVEL, level)
                }
            }
        }
    }

}

