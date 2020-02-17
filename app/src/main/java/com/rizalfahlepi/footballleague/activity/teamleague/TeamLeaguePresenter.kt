package com.rizalfahlepi.footballleague.activity.teamleague

import com.google.gson.Gson
import com.rizalfahlepi.footballleague.api.ApiRepository
import com.rizalfahlepi.footballleague.api.TheSportDBApi
import com.rizalfahlepi.footballleague.model.response.ListTeamLeague
import com.rizalfahlepi.footballleague.util.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamLeaguePresenter(private val view: TeamLeagueView,
                               private val apiRepository: ApiRepository,
                               private val gson: Gson,
                               private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getListTeam(idLeague: String?) {
        view.showLoading()

        GlobalScope.launch (context.main){
            val data = gson.fromJson(apiRepository
                .doRequestAsync(TheSportDBApi.getListTeam(idLeague)).await(),
                ListTeamLeague::class.java)

            view.showListTeamLeague(data.teams ?: ArrayList())
            view.hideLoading()
        }
    }
}