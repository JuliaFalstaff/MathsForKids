package com.example.mathsforkids.domain.usecase

import com.example.mathsforkids.domain.entity.GameSettings
import com.example.mathsforkids.domain.entity.Question
import com.example.mathsforkids.domain.repository.Repository

class GenerateQuestionUseCase(private val repository: Repository) {

    operator fun invoke(maxSumValue: Int): Question {
        return repository.generateQuestion(maxSumValue, COUNT_OF_OPTIONS)
    }

    companion object {
        private const val COUNT_OF_OPTIONS = 6
    }
}