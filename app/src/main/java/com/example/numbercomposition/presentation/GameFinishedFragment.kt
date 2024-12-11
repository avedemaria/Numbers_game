package com.example.numbercomposition.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.numbercomposition.R
import com.example.numbercomposition.databinding.FragmentGameFinishedBinding

class GameFinishedFragment : Fragment() {

    private val args by navArgs<GameFinishedFragmentArgs>()

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentWelcomeBinding is null")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonRetry.setOnClickListener {
            retryGame()
        }

        bindViews()


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    @SuppressLint("StringFormatMatches")
    private fun bindViews() {
        with(binding) {
            pictureResult.setImageResource(getPictureResId())
            tvRequiredAnswers.text = String.format(
                getString(R.string.required_answers),
                args.gameResult.gameSettings.minCountOfRightAnswers.toString()
            )
            tvScoreAnswers.text = String.format(
                getString(R.string.score_answers),
                args.gameResult.countOfRightAnswers.toString()
            )
            tvRequiredPercentage.text = String.format(
                getString(R.string.required_percentage),
                args.gameResult.gameSettings.minPercentOfRightAnswers.toString()
            )

            val scorePercentage = getScorePercentage()
            tvScorePercentage.text = String.format(
                getString(
                    R.string.score_percentage,
                    scorePercentage
                )
            )

        }
    }

    private fun getScorePercentage(): Int {
       return ((args.gameResult.countOfRightAnswers
               / args.gameResult.countOfQuestions.toDouble()) * 100)
            .toInt()
    }

    private fun getPictureResId(): Int {
        return if (args.gameResult.winner) {
            R.drawable.golden_cup_7825
        } else {
            R.drawable.cry_5141
        }
    }

//    private fun parseArgs() {
//        gameResult = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            requireArguments().getParcelable(KEY_GAME_RESULT, GameResult::class.java)
//        } else {
//            requireArguments().getParcelable(KEY_GAME_RESULT) as? GameResult
//        } ?: throw RuntimeException("Level is null")
//
//    }


    private fun retryGame() {
        findNavController().popBackStack()
    }


//    companion object {
//
//        const val KEY_GAME_RESULT = "game_result"
//
//        fun newInstance(gameResult: GameResult): GameFinishedFragment {
//            return GameFinishedFragment().apply {
//                arguments = Bundle().apply {
//                    putParcelable(KEY_GAME_RESULT, gameResult)
//                }
//            }
//        }
//    }
}