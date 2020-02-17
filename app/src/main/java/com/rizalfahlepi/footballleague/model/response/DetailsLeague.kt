package com.rizalfahlepi.footballleague.model.response

import com.google.gson.annotations.SerializedName

data class DetailsLeague (
    @SerializedName("leagues")
    var leagues: List<League>? = null
)