package com.example.numbercomposition.domain.usecases

import com.example.numbercomposition.domain.entity.GameSettings
import com.example.numbercomposition.domain.entity.Level
import com.example.numbercomposition.domain.repository.GameRepository

class GetGameSettingsUseCase(
    private val gameRepository: GameRepository
) {
    operator fun invoke(level: Level): GameSettings {
         return gameRepository.getGameSettings(level)
    }
}