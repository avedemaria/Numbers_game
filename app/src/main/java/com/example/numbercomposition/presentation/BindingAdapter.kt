package com.example.numbercomposition.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.numbercomposition.R
import com.example.numbercomposition.domain.entity.GameResult
import com.example.numbercomposition.domain.entity.GameSettings


interface OnOptionClickListener {

    fun onOptionClick(option: Int)
}

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
fun bindRightPercentage(textView: TextView, gameResult: GameResult) {
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
fun bindImageWinner(imageView: ImageView, winner: Boolean) {
    imageView.setImageResource(getPictureResId(winner))
}

private fun getPictureResId(winner: Boolean): Int {
    return if (winner) {
        R.drawable.golden_cup_7825
    } else {
        R.drawable.cry_5141
    }
}


@BindingAdapter("enoughAnswers")
fun bindEnoughCountOfAnswers(textView: TextView, isEnough: Boolean) {
    val colorResId = if (isEnough) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }

    val color = ContextCompat.getColor(textView.context, colorResId)
    textView.setTextColor(color)
}


@BindingAdapter("enoughPercent")
fun bindEnoughCountOfPercent(progressBar: ProgressBar, isEnough: Boolean) {
    val colorResId = if (isEnough) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }

    val color = ContextCompat.getColor(progressBar.context, colorResId)
    progressBar.progressTintList = ColorStateList.valueOf(color)
}

@BindingAdapter("numberAsString")
fun bindNumberAsText(textView: TextView, number: Int) {
    textView.text = number.toString()
}

@BindingAdapter("onOptionClickListener")
fun bindOnOptionClickListener(textView: TextView, clickListener: OnOptionClickListener) {
    textView.setOnClickListener {
        clickListener.onOptionClick(textView.text.toString().toInt())
    }
}
