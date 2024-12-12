package com.example.numbercomposition.presentation

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.numbercomposition.R
import com.example.numbercomposition.domain.entity.GameResult

@SuppressLint("StringFormatMatches")
@BindingAdapter("requiredAnswers")
fun bindRequiredAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_answers),
        count
    )
}

@SuppressLint("StringFormatMatches")
@BindingAdapter("requiredPercentage")
fun bindRequiredPercentage(textView: TextView, score: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage),
        score
    )
}

@SuppressLint("StringFormatMatches")
@BindingAdapter("scoreAnswers")
fun bindRightAnswers(textView: TextView, score: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        score
    )
}

@SuppressLint("StringFormatMatches")
@BindingAdapter("scorePercentage")
    fun bindRightPercentage (textView: TextView, gameResult: GameResult) {
       textView.text = String.format(
            textView.context.getString(R.string.score_percentage),
                getScorePercentage(gameResult)
            )
    }


private fun getScorePercentage(gameResult: GameResult): Int {
    return ((gameResult.countOfRightAnswers
            / gameResult.countOfQuestions.toDouble()) * 100)
        .toInt()
}

@BindingAdapter("imageWinner")
    fun bindImageWinner (imageView: ImageView, winner: Boolean) {
        imageView.setImageResource(getPictureResId(winner))
    }

private fun getPictureResId(winner: Boolean): Int {
    return if (winner) {
        R.drawable.golden_cup_7825
    } else {
        R.drawable.cry_5141
    }
}
