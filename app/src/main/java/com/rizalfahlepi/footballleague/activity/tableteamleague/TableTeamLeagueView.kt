package com.rizalfahlepi.footballleague.activity.tableteamleague

import com.rizalfahlepi.footballleague.model.response.Table

interface TableTeamLeagueView {
    fun showLoading()
    fun hideLoading()
    fun showListTableLeague(data: List<Table>)
}