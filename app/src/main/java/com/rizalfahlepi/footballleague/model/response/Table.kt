package com.rizalfahlepi.footballleague.model.response

import com.google.gson.annotations.SerializedName

data class Table (
    @SerializedName("name")
    var name: String? = null,

    @SerializedName("teamid")
    var teamid: String? = null,

    @SerializedName("played")
    var played: String? = null,

    @SerializedName("goalsfor")
    var goalsFor: String? = null,

    @SerializedName("goalsagainst")
    var goalsAgainst: String? = null,

    @SerializedName("goalsdifference")
    var goalsDifference: String? = null,

    @SerializedName("win")
    var win: String? = null,

    @SerializedName("draw")
    var draw: String? = null,

    @SerializedName("loss")
    var loss: String? = null,

    @SerializedName("total")
    var total: String? = null
)