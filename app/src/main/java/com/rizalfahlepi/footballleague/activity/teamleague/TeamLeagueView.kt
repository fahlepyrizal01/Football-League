package com.rizalfahlepi.footballleague.activity.teamleague

import com.rizalfahlepi.footballleague.model.response.Team

interface TeamLeagueView {
    fun showLoading()
    fun hideLoading()
    fun showListTeamLeague(data: List<Team>)
}