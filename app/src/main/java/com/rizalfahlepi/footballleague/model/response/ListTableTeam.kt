package com.rizalfahlepi.footballleague.model.response

import com.google.gson.annotations.SerializedName

data class ListTableTeam (
    @SerializedName("table")
    var table: List<Table>? = null
)