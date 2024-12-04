package com.example.numbercomposition.domain.usecases

import com.example.numbercomposition.domain.entity.Question
import com.example.numbercomposition.domain.repository.GameRepository

class GenerateQuestionUseCase(private val gameRepository: GameRepository) {

   operator fun invoke(maxSumValue: Int): Question {
      return gameRepository.generateQuestion(maxSumValue, COUNT_OF_OPTIONS)
   }


    companion object {
        private const val COUNT_OF_OPTIONS = 6

    }
}