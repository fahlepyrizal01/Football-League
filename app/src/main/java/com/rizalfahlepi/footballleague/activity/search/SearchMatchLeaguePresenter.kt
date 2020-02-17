package com.rizalfahlepi.footballleague.activity.search

import com.google.gson.Gson
import com.rizalfahlepi.footballleague.api.ApiRepository
import com.rizalfahlepi.footballleague.api.TheSportDBApi
import com.rizalfahlepi.footballleague.model.response.ListTeamLeague
import com.rizalfahlepi.footballleague.model.response.SearchMatch
import com.rizalfahlepi.footballleague.util.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchMatchLeaguePresenter(private val view: SearchMatchLeagueView,
                                 private val apiRepository: ApiRepository,
                                 private val gson: Gson,
                                 private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getListMatch(query: String?) {
        view.showLoading()

        GlobalScope.launch (context.main){
            val data = gson.fromJson(apiRepository
                .doRequestAsync(TheSportDBApi.searchMatch(query)).await(),
                SearchMatch::class.java)

            view.showListMatchLeague(data.event ?: ArrayList())
            view.hideLoading()
        }
    }

    fun getListTeam(query: String?) {
        view.showLoading()

        GlobalScope.launch (context.main){
            val data = gson.fromJson(apiRepository
                .doRequestAsync(TheSportDBApi.searchTeam(query)).await(),
                ListTeamLeague::class.java)

            view.showListTeamLeague(data.teams ?: ArrayList())
            view.hideLoading()
        }
    }
}