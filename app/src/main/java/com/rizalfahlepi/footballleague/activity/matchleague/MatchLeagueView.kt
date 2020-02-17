package com.rizalfahlepi.footballleague.activity.matchleague

import com.rizalfahlepi.footballleague.model.response.Event
import com.rizalfahlepi.footballleague.model.response.League

interface MatchLeagueView {
    fun showLoading()
    fun hideLoading()
    fun showDetailsLeague(data: List<League>)
    fun showListLastMatchLeague(data: List<Event>)
    fun showListNextMatchLeague(data: List<Event>)
}