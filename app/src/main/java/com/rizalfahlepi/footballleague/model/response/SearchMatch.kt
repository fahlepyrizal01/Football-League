package com.rizalfahlepi.footballleague.model.response

import com.google.gson.annotations.SerializedName

data class SearchMatch (
    @SerializedName("event")
    var event: List<Event>? = null
)
