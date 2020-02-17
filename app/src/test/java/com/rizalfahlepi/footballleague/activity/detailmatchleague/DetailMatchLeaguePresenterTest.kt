package com.rizalfahlepi.footballleague.activity.detailmatchleague

import com.google.gson.Gson
import com.rizalfahlepi.footballleague.TestContextProvider
import com.rizalfahlepi.footballleague.api.ApiRepository
import com.rizalfahlepi.footballleague.model.response.ListTeamLeague
import com.rizalfahlepi.footballleague.model.response.Event
import com.rizalfahlepi.footballleague.model.response.ListMatchLeague
import com.rizalfahlepi.footballleague.model.response.Team
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Before
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailMatchLeaguePresenterTest {

    @Mock
    private lateinit var view: DetailMatchLeagueView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: DetailMatchLeaguePresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailMatchLeaguePresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetDetailsMatchLeague() {
        val leagues: MutableList<Event> = mutableListOf()
        val response = ListMatchLeague(leagues)
        val idEvent = "1234"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    "",
                    ListMatchLeague::class.java
                )
            ).thenReturn(response)

            presenter.getDetailsMatchLeague(idEvent)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showDetailsMatchLeague(leagues)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun testGetDetailsTeamHomeLeague() {
        val teams: MutableList<Team> = mutableListOf()
        val response = ListTeamLeague(teams)
        val idTeam = "1234"

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

            presenter.getDetailsTeamHomeLeague(idTeam)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showDetailsTeamHomeLeague(teams)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun testGetDetailsTeamAwayLeague() {
        val teams: MutableList<Team> = mutableListOf()
        val response = ListTeamLeague(teams)
        val idTeam = "1234"

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

            presenter.getDetailsTeamAwayLeague(idTeam)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showDetailsTeamAwayLeague(teams)
            Mockito.verify(view).hideLoading()
        }
    }
}