package com.rizalfahlepi.footballleague.model.response

import com.google.gson.annotations.SerializedName

data class League (
    @SerializedName("idLeague")
    var idLeague: String? = null,

    @SerializedName("strSport")
    var strSport: String? = null,

    @SerializedName("strLeague")
    var strLeague: String? = null,

    @SerializedName("intFormedYear")
    var intFormedYear: String? = null,

    @SerializedName("dateFirstEvent")
    var dateFirstEvent: String? = null,

    @SerializedName("strGender")
    var strGender: String? = null,

    @SerializedName("strCountry")
    var strCountry: String? = null,

    @SerializedName("strWebsite")
    var strWebsite: String? = null,

    @SerializedName("strFacebook")
    var strFacebook: String? = null,

    @SerializedName("strTwitter")
    var strTwitter: String? = null,

    @SerializedName("strYoutube")
    var strYoutube: String? = null,

    @SerializedName("strBadge")
    var strBadge: String? = null
)