package net.iqbalfauzan.mykotlinapp.submission_akhir.Presenter

import com.google.gson.Gson
import net.iqbalfauzan.mykotlinapp.ApiRepository
import net.iqbalfauzan.mykotlinapp.submission_akhir.ApiService
import net.iqbalfauzan.mykotlinapp.submission_akhir.Model.ModelMatch
import net.iqbalfauzan.mykotlinapp.submission_akhir.Response.NextMatchResponse
import net.iqbalfauzan.mykotlinapp.submission_akhir.Response.PrevMatchResponse
import net.iqbalfauzan.mykotlinapp.submission_akhir.View.NextMatchView
import net.iqbalfauzan.mykotlinapp.submission_akhir.View.PrevMatchView
import net.iqbalfauzan.mykotlinapp.utils.TestContextProvider
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PrevMatchPresenterTest{
    @Mock
    private
    lateinit var view: PrevMatchView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository
    private lateinit var presenter: PrevMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = PrevMatchPresenter(view, apiRepository, gson, TestContextProvider())
    }


    @Test
    fun getMatchList() {
        val teams: MutableList<ModelMatch> = mutableListOf()
        val response = PrevMatchResponse(teams)
        val league = "4328"

        Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(ApiService.getNextMatch(league)),
                PrevMatchResponse::class.java
        )).thenReturn(response)

        presenter.getMatchList(league)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showPrevMatch(teams)
        Mockito.verify(view).hideLoading()
    }
}