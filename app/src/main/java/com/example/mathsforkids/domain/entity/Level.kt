package com.example.mathsforkids.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class Level: Parcelable {
    TEST, EASY, NORMAL, HARD
}