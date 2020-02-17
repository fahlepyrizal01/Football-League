package com.rizalfahlepi.footballleague.activity.detailmatchleague

import com.google.gson.Gson
import com.rizalfahlepi.footballleague.api.ApiRepository
import com.rizalfahlepi.footballleague.api.TheSportDBApi
import com.rizalfahlepi.footballleague.model.response.ListTeamLeague
import com.rizalfahlepi.footballleague.model.response.ListMatchLeague
import com.rizalfahlepi.footballleague.util.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailMatchLeaguePresenter(private val view: DetailMatchLeagueView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getDetailsMatchLeague(idEvent: String?) {
        view.showLoading()

        GlobalScope.launch (context.main){
            val data = gson.fromJson(apiRepository
                .doRequestAsync(TheSportDBApi.getDetailsMatch(idEvent)).await(),
                ListMatchLeague::class.java)

            view.showDetailsMatchLeague(data.events ?: ArrayList())
            view.hideLoading()
        }
    }

    fun getDetailsTeamHomeLeague(idTeam: String?) {
        view.showLoading()

        GlobalScope.launch (context.main){
            val data = gson.fromJson(apiRepository
                .doRequestAsync(TheSportDBApi.getDetailTeam(idTeam)).await(),
                ListTeamLeague::class.java)

            view.showDetailsTeamHomeLeague(data.teams ?: ArrayList())
            view.hideLoading()
        }
    }

    fun getDetailsTeamAwayLeague(idTeam: String?) {
        view.showLoading()

        GlobalScope.launch (context.main){
            val data = gson.fromJson(apiRepository
                .doRequestAsync(TheSportDBApi.getDetailTeam(idTeam)).await(),
                ListTeamLeague::class.java)

            view.showDetailsTeamAwayLeague(data.teams ?: ArrayList())
            view.hideLoading()
        }
    }
}