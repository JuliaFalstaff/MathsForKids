package com.example.mathsforkids.utils

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mathsforkids.domain.entity.Level
import com.example.mathsforkids.presentation.GameViewModel


class GameViewModelFactory(
    private val level: Level,
    private val application: Application
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            return GameViewModel(level, application) as T
        }
        throw RuntimeException("Unknown view model class $modelClass")
    }
}