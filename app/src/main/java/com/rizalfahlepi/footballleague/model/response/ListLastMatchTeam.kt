package com.rizalfahlepi.footballleague.model.response

import com.google.gson.annotations.SerializedName

data class ListLastMatchTeam (
    @SerializedName("results")
    var results: List<Event>? = null
)