package com.example.numbercomposition.presentation

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.numbercomposition.R
import com.example.numbercomposition.data.GameRepositoryImpl
import com.example.numbercomposition.domain.entity.GameResult
import com.example.numbercomposition.domain.entity.GameSettings
import com.example.numbercomposition.domain.entity.Level
import com.example.numbercomposition.domain.entity.Question
import com.example.numbercomposition.domain.usecases.GenerateQuestionUseCase
import com.example.numbercomposition.domain.usecases.GetGameSettingsUseCase

class GameViewModel(
    private val level: Level,
    private val application: Application
    ) : ViewModel() {


    private lateinit var gameSettings: GameSettings

    private var timer: CountDownTimer? = null

    private val gameRepository = GameRepositoryImpl
    private val getGameSettingsUseCase = GetGameSettingsUseCase(gameRepository)
    private val generateQuestionUseCase = GenerateQuestionUseCase(gameRepository)

    private val _question = MutableLiveData<Question>()
    val question: LiveData<Question> get() = _question

    private val _formatedTime = MutableLiveData<String>()
    val formattedTime: LiveData<String> get() = _formatedTime

    private val _percentOfRightAnswers = MutableLiveData<Int>()
    val percentOfRightAnswers: LiveData<Int> get() = _percentOfRightAnswers

    private val _progressAnswers = MutableLiveData<String>()
    val progressAnswers: LiveData<String> get() = _progressAnswers

    private val _isPercentOfAnswersEnough = MutableLiveData<Boolean>()
    val isPercentOfAnswersEnough: LiveData<Boolean> get() = _isPercentOfAnswersEnough

    private val _isCountOfAnswersEnough = MutableLiveData<Boolean>()
    val isCountOfAnswersEnough: LiveData<Boolean> get() = _isCountOfAnswersEnough

    private val _secondaryProgress = MutableLiveData<Int>()
    val secondaryProgress: LiveData<Int> get() = _secondaryProgress

    private val _gameResult = MutableLiveData<GameResult>()
    val gameResult: LiveData<GameResult> get() = _gameResult


    private var countOfRightAnswers = 0
    private var countOfQuestions = 0


    init {
        startGame()
    }

    private fun startGame() {
        this.gameSettings = getGameSettingsUseCase(level)
        _secondaryProgress.value = gameSettings.minPercentOfRightAnswers
        startTimer()
        generateQuestion()
        updateProgress()
    }

    private fun finishGame() {

        _gameResult.value = GameResult(
            winner = isCountOfAnswersEnough.value == true && isPercentOfAnswersEnough.value == true,
            countOfRightAnswers = countOfRightAnswers,
            countOfQuestions = countOfQuestions,
            gameSettings = gameSettings
        )

    }

    private fun updateProgress() {

        if (countOfQuestions == 0) {
            val percentOfRightAnswers = ((countOfRightAnswers / countOfQuestions.toDouble()) * 100)
                .toInt()
            _percentOfRightAnswers.value = percentOfRightAnswers
            _progressAnswers.value = String.format(
                application.resources.getString(R.string.progress_answers),
                0,
                gameSettings.minCountOfRightAnswers)
        }

        val percentOfRightAnswers = ((countOfRightAnswers / countOfQuestions.toDouble()) * 100)
            .toInt()
        _percentOfRightAnswers.value = percentOfRightAnswers
        _progressAnswers.value = String.format(
            application.resources.getString(R.string.progress_answers),
            countOfRightAnswers,
            gameSettings.minCountOfRightAnswers
        )

        _isCountOfAnswersEnough.value = countOfRightAnswers >= gameSettings.minCountOfRightAnswers
        _isPercentOfAnswersEnough.value =
            percentOfRightAnswers >= gameSettings.minPercentOfRightAnswers

    }


    fun chooseAnswer(number: Int) {
        val rightAnswer = question.value?.rightAnswer

        if (number == rightAnswer) {
            countOfRightAnswers++
        }
        countOfQuestions++

        updateProgress()
        generateQuestion()

    }

    private fun startTimer() {
        timer = object : CountDownTimer(
            gameSettings.gameTimeInSeconds * MILLIS_IN_SECONDS,
            MILLIS_IN_SECONDS
        ) {

            override fun onTick(millisUntilFinished: Long) {
                _formatedTime.value = formatTime(millisUntilFinished)
            }

            override fun onFinish() {
                finishGame()
            }
        }

        timer?.start()
    }


    private fun formatTime(millisUntilFinished: Long): String {
        val seconds = millisUntilFinished / MILLIS_IN_SECONDS
        val minutes = seconds / 60
        val leftSeconds = seconds - (minutes * 60)
        return String.format("%02d:%02d", minutes, leftSeconds)
    }


    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }

    private fun generateQuestion() {
        _question.value = generateQuestionUseCase(gameSettings.maxSumValue)
    }

    companion object {
        private const val MILLIS_IN_SECONDS = 1000L
    }
}