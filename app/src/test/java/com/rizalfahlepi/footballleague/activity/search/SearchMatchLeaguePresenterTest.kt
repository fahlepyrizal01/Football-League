package com.rizalfahlepi.footballleague.activity.search

import com.google.gson.Gson
import com.rizalfahlepi.footballleague.TestContextProvider
import com.rizalfahlepi.footballleague.api.ApiRepository
import com.rizalfahlepi.footballleague.model.response.Event
import com.rizalfahlepi.footballleague.model.response.ListTeamLeague
import com.rizalfahlepi.footballleague.model.response.SearchMatch
import com.rizalfahlepi.footballleague.model.response.Team
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Before
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SearchMatchLeaguePresenterTest {

    @Mock
    private lateinit var view: SearchMatchLeagueView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: SearchMatchLeaguePresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SearchMatchLeaguePresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetListMatchLeague() {
        val leagues: MutableList<Event> = mutableListOf()
        val response = SearchMatch(leagues)
        val query = "Madrid"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    "",
                    SearchMatch::class.java
                )
            ).thenReturn(response)

            presenter.getListMatch(query)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showListMatchLeague(leagues)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun testGetListTeamLeague() {
        val teams: MutableList<Team> = mutableListOf()
        val response = ListTeamLeague(teams)
        val query = "Barcelona"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    "",
                    ListTeamLeague::class.java
                )
            ).thenReturn(response)

            presenter.getListTeam(query)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showListTeamLeague(teams)
            Mockito.verify(view).hideLoading()
        }
    }
}