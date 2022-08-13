package com.example.mathsforkids.domain.repository

import com.example.mathsforkids.domain.entity.GameSettings
import com.example.mathsforkids.domain.entity.Level
import com.example.mathsforkids.domain.entity.Question

interface Repository {
    fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question
    fun getGameSettings(level: Level): GameSettings
}