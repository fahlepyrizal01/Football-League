package com.rizalfahlepi.footballleague.activity.search

import com.rizalfahlepi.footballleague.model.response.Event
import com.rizalfahlepi.footballleague.model.response.Team

interface SearchMatchLeagueView {
    fun showLoading()
    fun hideLoading()
    fun showListMatchLeague(data: List<Event>)
    fun showListTeamLeague(data: List<Team>)
}