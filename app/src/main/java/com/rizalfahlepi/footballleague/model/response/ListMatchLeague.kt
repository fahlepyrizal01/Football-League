package com.rizalfahlepi.footballleague.model.response

import com.google.gson.annotations.SerializedName

data class ListMatchLeague (
    @SerializedName("events")
    var events: List<Event>? = null
)