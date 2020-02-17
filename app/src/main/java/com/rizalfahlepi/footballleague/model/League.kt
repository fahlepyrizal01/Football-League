package com.rizalfahlepi.footballleague.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League (
    var flag: Int,
    var logo: Int,
    var id: String,
    var title: String,
    var description: String
) : Parcelable