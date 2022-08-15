package com.example.mathsforkids.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.mathsforkids.R
import com.example.mathsforkids.databinding.FragmentGameFinishedBinding
import com.example.mathsforkids.domain.entity.GameResult


class GameFinishedFragment : Fragment() {

    private lateinit var gameResult: GameResult

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseGameResult()
    }

    private fun parseGameResult() {
        requireArguments().getParcelable<GameResult>(KEY_GAME_RESULT)?.let {
            gameResult = it
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentGameFinishedBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDameResultData()
        setOnBackPressedCallback()
        retryGameAgain()
    }


    private fun setDameResultData() = with(binding) {
        textViewRequiredAnswers.text = String.format(
                getString(R.string.required_score),
                gameResult.gameSettings.minCountOfRightAnswers
        )
        textViewRequiredPercentage.text = String.format(
                getString(R.string.required_percentage),
                gameResult.gameSettings.minPercentOfRightAnswers
        )
        textViewScoreAnswers.text = String.format(
                getString(R.string.score_answers),
                gameResult.countOfRightAnswers
        )
        textViewScorePercentage.text = String.format(
                getString(R.string.score_percentage),
                getPercentOfRightAnswers()
        )
        if (gameResult.winner) {
            imageViewEmojiResult.setImageResource(R.drawable.ic_launcher_foreground)
        } else {
            imageViewEmojiResult.setImageResource(R.drawable.ic_launcher_background)
        }
    }

    private fun getPercentOfRightAnswers() = with(gameResult) {
        if (countOfQuestions == 0) {
            0
        } else {
            ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
        }
    }

    private fun retryGameAgain() {
        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
    }

    private fun setOnBackPressedCallback() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        retryGame()
                    }
                })
    }

    private fun retryGame() {
        requireActivity().supportFragmentManager.popBackStack(
                GameFragment.NAME,
                FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val KEY_GAME_RESULT = "result"

        @JvmStatic
        fun newInstance(gameResult: GameResult): GameFinishedFragment {
            return GameFinishedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_GAME_RESULT, gameResult)
                }
            }
        }
    }
}