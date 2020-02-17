package com.rizalfahlepi.footballleague.activity.detailteam

import com.rizalfahlepi.footballleague.model.response.Event
import com.rizalfahlepi.footballleague.model.response.Team

interface DetailTeamView {
    fun showLoading()
    fun hideLoading()
    fun showDetailsTeam(data: List<Team>)
    fun showListLastMatchLeague(data: List<Event>)
    fun showListNextMatchLeague(data: List<Event>)
}