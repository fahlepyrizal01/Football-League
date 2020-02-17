package com.rizalfahlepi.footballleague.model.response

import com.google.gson.annotations.SerializedName

data class ListTeamLeague (
    @SerializedName("teams")
    var teams: List<Team>? = null
)