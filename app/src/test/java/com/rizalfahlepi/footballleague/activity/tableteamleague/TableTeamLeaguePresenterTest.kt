package com.rizalfahlepi.footballleague.activity.tableteamleague

import com.google.gson.Gson
import com.rizalfahlepi.footballleague.TestContextProvider
import com.rizalfahlepi.footballleague.api.ApiRepository
import com.rizalfahlepi.footballleague.model.response.ListTableTeam
import com.rizalfahlepi.footballleague.model.response.Table
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TableTeamLeaguePresenterTest {

    @Mock
    private lateinit var view: TableTeamLeagueView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: TableTeamLeaguePresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = TableTeamLeaguePresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetListTable() {
        val leagues: MutableList<Table> = mutableListOf()
        val response = ListTableTeam(leagues)
        val idLeague = "1234"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    "",
                    ListTableTeam::class.java
                )
            ).thenReturn(response)

            presenter.getListTable(idLeague)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showListTableLeague(leagues)
            Mockito.verify(view).hideLoading()
        }
    }
}