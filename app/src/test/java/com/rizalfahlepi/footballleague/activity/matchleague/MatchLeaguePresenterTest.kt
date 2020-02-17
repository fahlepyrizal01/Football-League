package com.rizalfahlepi.footballleague.activity.matchleague

import com.google.gson.Gson
import com.rizalfahlepi.footballleague.TestContextProvider
import com.rizalfahlepi.footballleague.api.ApiRepository
import com.rizalfahlepi.footballleague.model.response.DetailsLeague
import com.rizalfahlepi.footballleague.model.response.Event
import com.rizalfahlepi.footballleague.model.response.League
import com.rizalfahlepi.footballleague.model.response.ListMatchLeague
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchLeaguePresenterTest {

    @Mock
    private lateinit var view: MatchLeagueView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: MatchLeaguePresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchLeaguePresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetDetailsLeague() {
        val leagues: MutableList<League> = mutableListOf()
        val response = DetailsLeague(leagues)
        val idLeague = "1234"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    "",
                    DetailsLeague::class.java
                )
            ).thenReturn(response)

            presenter.getDetailsLeague(idLeague)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showDetailsLeague(leagues)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun testGetListLastMatch() {
        val events: MutableList<Event> = mutableListOf()
        val response = ListMatchLeague(events)
        val idLeague = "1234"

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

            presenter.getListLastMatch(idLeague)
            Mockito.verify(view).showLoading()
            Mockito.verify(view).showListLastMatchLeague(events)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun testGetListNextMatch() {
        val events: MutableList<Event> = mutableListOf()
        val response = ListMatchLeague(events)
        val idLeague = "1234"

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

            presenter.getListNextMatch(idLeague)
            Mockito.verify(view).showLoading()
            Mockito.verify(view).showListNextMatchLeague(events)
            Mockito.verify(view).hideLoading()
        }
    }
}