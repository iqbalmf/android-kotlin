package net.iqbalfauzan.mykotlinapp.submission_akhir.Presenter

import com.google.gson.Gson
import net.iqbalfauzan.mykotlinapp.ApiRepository
import net.iqbalfauzan.mykotlinapp.submission_akhir.ApiService
import net.iqbalfauzan.mykotlinapp.submission_akhir.Model.ModelMatch
import net.iqbalfauzan.mykotlinapp.submission_akhir.Model.ModelTeam
import net.iqbalfauzan.mykotlinapp.submission_akhir.Response.NextMatchResponse
import net.iqbalfauzan.mykotlinapp.submission_akhir.Response.TeamResponse
import net.iqbalfauzan.mykotlinapp.submission_akhir.View.NextMatchView
import net.iqbalfauzan.mykotlinapp.submission_akhir.View.TeamView
import net.iqbalfauzan.mykotlinapp.utils.TestContextProvider
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TeamPresenterTest{
    @Mock
    private
    lateinit var view: TeamView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository
    private lateinit var presenter: TeamPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = TeamPresenter(view, apiRepository, gson, TestContextProvider())
    }


    @Test
    fun getTeamList() {
        val teams: MutableList<ModelTeam> = mutableListOf()
        val response = TeamResponse(teams)
        val league = "English Premier League"

        Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(ApiService.getTeams(league)),
                TeamResponse::class.java
        )).thenReturn(response)

        presenter.getTeamList(league)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showTeamList(teams)
        Mockito.verify(view).hideLoading()
    }
}