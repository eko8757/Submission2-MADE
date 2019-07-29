package com.made.movietv.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ModelMovie(
    val name: String,
    val image: String,
    val overview: String,
    val release: String,
    val popularity: Int,
    val vote: Int
) : Parcelable