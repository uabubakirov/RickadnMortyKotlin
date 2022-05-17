package com.example.rickadnmortykotlin.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FilterData(
    val gender: String? = null,
    val status: String? = null
):Parcelable
