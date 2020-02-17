package com.rizalfahlepi.footballleague.activity.detailmatchleague

import com.rizalfahlepi.footballleague.model.response.Event
import com.rizalfahlepi.footballleague.model.response.Team

interface DetailMatchLeagueView {
    fun showLoading()
    fun hideLoading()
    fun showDetailsMatchLeague(data: List<Event>)
    fun showDetailsTeamHomeLeague(data: List<Team>)
    fun showDetailsTeamAwayLeague(data: List<Team>)
}