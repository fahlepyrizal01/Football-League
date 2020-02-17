package com.rizalfahlepi.footballleague.activity.matchleague

import com.google.gson.Gson
import com.rizalfahlepi.footballleague.api.ApiRepository
import com.rizalfahlepi.footballleague.api.TheSportDBApi
import com.rizalfahlepi.footballleague.model.response.DetailsLeague
import com.rizalfahlepi.footballleague.model.response.ListMatchLeague
import com.rizalfahlepi.footballleague.util.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MatchLeaguePresenter(private val view: MatchLeagueView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getDetailsLeague(idLeague: String?) {
        view.showLoading()

        GlobalScope.launch (context.main){
            val data = gson.fromJson(apiRepository
                .doRequestAsync(TheSportDBApi.getDetailsLeague(idLeague)).await(),
                DetailsLeague::class.java)

            view.showDetailsLeague(data.leagues ?: ArrayList())
            view.hideLoading()
        }
    }

    fun getListLastMatch(idLeague: String?) {
        view.showLoading()

        GlobalScope.launch (context.main){
            val data = gson.fromJson(apiRepository
                .doRequestAsync(TheSportDBApi.getListLastMatch(idLeague)).await(),
                ListMatchLeague::class.java)

            view.showListLastMatchLeague(data.events ?: ArrayList())
            view.hideLoading()
        }
    }

    fun getListNextMatch(idLeague: String?) {
        view.showLoading()

        GlobalScope.launch (context.main){
            val data = gson.fromJson(apiRepository
                .doRequestAsync(TheSportDBApi.getListNextMatch(idLeague)).await(),
                ListMatchLeague::class.java)

            view.showListNextMatchLeague(data.events ?: ArrayList())
            view.hideLoading()
        }
    }
}