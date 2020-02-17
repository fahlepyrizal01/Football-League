package com.rizalfahlepi.footballleague.model.response

import com.google.gson.annotations.SerializedName


data class Team (
    @SerializedName("idTeam")
    var idTeam: String? = null,

    @SerializedName("strTeam")
    var strTeam: String? = null,

    @SerializedName("intFormedYear")
    var intFormedYear: String? = null,

    @SerializedName("strLeague")
    var strLeague: String? = null,

    @SerializedName("idLeague")
    var idLeague: String? = null,

    @SerializedName("strStadium")
    var strStadium: String? = null,

    @SerializedName("strSport")
    var strSport: String? = null,

    @SerializedName("strStadiumThumb")
    var strStadiumThumb: String? = null,

    @SerializedName("strStadiumDescription")
    var strStadiumDescription: String? = null,

    @SerializedName("strStadiumLocation")
    var strStadiumLocation: String? = null,

    @SerializedName("intStadiumCapacity")
    var intStadiumCapacity: String? = null,

    @SerializedName("strWebsite")
    var strWebsite: String? = null,

    @SerializedName("strFacebook")
    var strFacebook: String? = null,

    @SerializedName("strTwitter")
    var strTwitter: String? = null,

    @SerializedName("strInstagram")
    var strInstagram: String? = null,

    @SerializedName("strDescriptionEN")
    var strDescriptionEN: String? = null,

    @SerializedName("strGender")
    var strGender: String? = null,

    @SerializedName("strCountry")
    var strCountry: String? = null,
    
    @SerializedName("strTeamBadge")
    var strTeamBadge: String? = null,
    
    @SerializedName("strTeamJersey")
    var strTeamJersey: String? = null,

    @SerializedName("strYoutube")
    var strYoutube: String? = null
)