package com.rizalfahlepi.footballleague.api

import com.rizalfahlepi.footballleague.BuildConfig

object TheSportDBApi {
    fun getDetailsLeague(idLeague: String?): String {
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupleague.php?id=$idLeague"
    }

    fun getListLastMatch(idLeague: String?): String {
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/eventspastleague.php?id=$idLeague"
    }

    fun getListNextMatch(idLeague: String?): String {
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/eventsnextleague.php?id=$idLeague"
    }

    fun getDetailsMatch(idEvent: String?): String {
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupevent.php?id=$idEvent"
    }

    fun searchMatch(query: String?): String {
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/searchevents.php?e=$query"
    }

    fun getListTeam(idLeague: String?): String {
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/lookup_all_teams.php?id=$idLeague"
    }

    fun getDetailTeam(idTeam: String?): String {
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupteam.php?id=$idTeam"
    }

    fun getTableLeague(idLeague: String?): String {
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/lookuptable.php?l=$idLeague"
    }

    fun getListLastMatchTeam(idTeam: String?): String {
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/eventslast.php?id=$idTeam"
    }

    fun getListNextMatchTeam(idTeam: String?): String {
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/eventsnext.php?id=$idTeam"
    }

    fun searchTeam(query: String?): String {
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/searchteams.php?t=$query"
    }
}