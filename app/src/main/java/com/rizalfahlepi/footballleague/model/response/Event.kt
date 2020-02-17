package com.rizalfahlepi.footballleague.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Event (
    @SerializedName("idEvent")
    var idEvent: String? = null,

    @SerializedName("strSport")
    var strSport: String? = null,

    @SerializedName("idLeague")
    var idLeague: String? = null,

    @SerializedName("strLeague")
    var strLeague: String? = null,

    @SerializedName("strSeason")
    var strSeason: String? = null,

    @SerializedName("strHomeTeam")
    var strHomeTeam: String? = null,

    @SerializedName("strAwayTeam")
    var strAwayTeam: String? = null,

    @SerializedName("intHomeScore")
    var intHomeScore: String? = null,

    @SerializedName("intAwayScore")
    var intAwayScore: String? = null,

    @SerializedName("strHomeGoalDetails")
    var strHomeGoalDetails: String? = null,

    @SerializedName("strHomeRedCards")
    var strHomeRedCards: String? = null,

    @SerializedName("strHomeYellowCards")
    var strHomeYellowCards: String? = null,

    @SerializedName("strHomeFormation")
    var strHomeFormation: String? = null,

    @SerializedName("strAwayRedCards")
    var strAwayRedCards: String? = null,

    @SerializedName("strAwayYellowCards")
    var strAwayYellowCards: String? = null,

    @SerializedName("strAwayGoalDetails")
    var strAwayGoalDetails: String? = null,

    @SerializedName("strAwayFormation")
    var strAwayFormation: String? = null,

    @SerializedName("intHomeShots")
    var intHomeShots: Int? = null,

    @SerializedName("intAwayShots")
    var intAwayShots: Int? = null,

    @SerializedName("dateEvent")
    var dateEvent: String? = null,

    @SerializedName("strDate")
    var strDate: String? = null,

    @SerializedName("strTime")
    var strTime: String? = null,

    @SerializedName("idHomeTeam")
    var idHomeTeam: String? = null,

    @SerializedName("idAwayTeam")
    var idAwayTeam: String? = null,

    var dtLocaleDate: Date? = null
): Parcelable