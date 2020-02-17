package com.rizalfahlepi.footballleague.activity.detailteam

import com.google.gson.Gson
import com.rizalfahlepi.footballleague.TestContextProvider
import com.rizalfahlepi.footballleague.api.ApiRepository
import com.rizalfahlepi.footballleague.model.response.*
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailTeamPresenterTest {

    @Mock
    private lateinit var view: DetailTeamView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: DetailTeamPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailTeamPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetDetailsTeam() {
        val leagues: MutableList<Team> = mutableListOf()
        val response = ListTeamLeague(leagues)
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

            presenter.getDetailsTeam(idTeam)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showDetailsTeam(leagues)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun testGetListLastMatch() {
        val events: MutableList<Event> = mutableListOf()
        val response = ListLastMatchTeam(events)
        val idTeam = "1234"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    "",
                    ListLastMatchTeam::class.java
                )
            ).thenReturn(response)

            presenter.getListLastMatch(idTeam)
            Mockito.verify(view).showLoading()
            Mockito.verify(view).showListLastMatchLeague(events)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun testGetListNextMatch() {
        val events: MutableList<Event> = mutableListOf()
        val response = ListMatchLeague(events)
        val idTeam = "1234"

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

            presenter.getListNextMatch(idTeam)
            Mockito.verify(view).showLoading()
            Mockito.verify(view).showListNextMatchLeague(events)
            Mockito.verify(view).hideLoading()
        }
    }
}