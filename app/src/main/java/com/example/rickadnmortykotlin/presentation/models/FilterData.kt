package com.example.rickadnmortykotlin.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FilterData(
    val param1: String? = null,
    val param2: String? = null,
    val param3: String? = null
):Parcelable
