package com.kalfian.submissionMoviecatalogtvshow.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ModelTv(
    val name: String,
    val image: String,
    val overview: String,
    val firstAirDate: String,
    val popularity: Int,
    val vote: Int
) : Parcelable