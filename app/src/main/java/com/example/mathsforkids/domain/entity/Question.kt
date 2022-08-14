package com.example.mathsforkids.domain.entity

data class Question(
    val sum: Int,
    val visibleNumbers: Int,
    val options: List<Int>
) {
    val rightAnswer: Int
        get() = sum - visibleNumbers
}
