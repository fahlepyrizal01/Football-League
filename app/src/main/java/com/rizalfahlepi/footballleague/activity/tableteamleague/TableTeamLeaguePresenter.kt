package com.rizalfahlepi.footballleague.activity.tableteamleague

import com.google.gson.Gson
import com.rizalfahlepi.footballleague.api.ApiRepository
import com.rizalfahlepi.footballleague.api.TheSportDBApi
import com.rizalfahlepi.footballleague.model.response.ListTableTeam
import com.rizalfahlepi.footballleague.util.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TableTeamLeaguePresenter(private val view: TableTeamLeagueView,
                                 private val apiRepository: ApiRepository,
                                 private val gson: Gson,
                                 private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getListTable(idLeague: String?) {
        view.showLoading()

        GlobalScope.launch (context.main){
            val data = gson.fromJson(apiRepository
                .doRequestAsync(TheSportDBApi.getTableLeague(idLeague)).await(),
                ListTableTeam::class.java)

            view.showListTableLeague(data.table ?: ArrayList())
            view.hideLoading()
        }
    }
}