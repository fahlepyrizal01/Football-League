package com.rizalfahlepi.footballleague.activity.teamleague

import com.google.gson.Gson
import com.rizalfahlepi.footballleague.TestContextProvider
import com.rizalfahlepi.footballleague.api.ApiRepository
import com.rizalfahlepi.footballleague.model.response.ListTeamLeague
import com.rizalfahlepi.footballleague.model.response.Team
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TeamLeaguePresenterTest {

    @Mock
    private lateinit var view: TeamLeagueView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: TeamLeaguePresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = TeamLeaguePresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetListTeam() {
        val leagues: MutableList<Team> = mutableListOf()
        val response = ListTeamLeague(leagues)
        val idLeague = "1234"

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

            presenter.getListTeam(idLeague)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showListTeamLeague(leagues)
            Mockito.verify(view).hideLoading()
        }
    }
}