package com.example.mathsforkids.domain.usecase

import com.example.mathsforkids.domain.entity.GameSettings
import com.example.mathsforkids.domain.entity.Level
import com.example.mathsforkids.domain.repository.Repository

class GetGameSettingsUseCase(private val repository: Repository) {
    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSettings(level)
    }
}