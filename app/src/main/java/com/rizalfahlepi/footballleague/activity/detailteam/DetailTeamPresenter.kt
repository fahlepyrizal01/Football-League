package com.rizalfahlepi.footballleague.activity.detailteam

import com.google.gson.Gson
import com.rizalfahlepi.footballleague.api.ApiRepository
import com.rizalfahlepi.footballleague.api.TheSportDBApi
import com.rizalfahlepi.footballleague.model.response.ListLastMatchTeam
import com.rizalfahlepi.footballleague.model.response.ListMatchLeague
import com.rizalfahlepi.footballleague.model.response.ListTeamLeague
import com.rizalfahlepi.footballleague.util.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailTeamPresenter(private val view: DetailTeamView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson,
                          private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getDetailsTeam(idTeam: String?) {
        view.showLoading()

        GlobalScope.launch (context.main){
            val data = gson.fromJson(apiRepository
                .doRequestAsync(TheSportDBApi.getDetailTeam(idTeam)).await(),
                ListTeamLeague::class.java)

            view.showDetailsTeam(data.teams ?: ArrayList())
            view.hideLoading()
        }
    }

    fun getListLastMatch(idLeague: String?) {
        view.showLoading()

        GlobalScope.launch (context.main){
            val data = gson.fromJson(apiRepository
                .doRequestAsync(TheSportDBApi.getListLastMatchTeam(idLeague)).await(),
                ListLastMatchTeam::class.java)

            view.showListLastMatchLeague(data.results ?: ArrayList())
            view.hideLoading()
        }
    }

    fun getListNextMatch(idLeague: String?) {
        view.showLoading()

        GlobalScope.launch (context.main){
            val data = gson.fromJson(apiRepository
                .doRequestAsync(TheSportDBApi.getListNextMatchTeam(idLeague)).await(),
                ListMatchLeague::class.java)

            view.showListNextMatchLeague(data.events ?: ArrayList())
            view.hideLoading()
        }
    }
}