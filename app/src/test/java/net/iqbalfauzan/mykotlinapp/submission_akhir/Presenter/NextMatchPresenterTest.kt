package net.iqbalfauzan.mykotlinapp.submission_akhir.Presenter

import com.google.gson.Gson
import kotlinx.coroutines.experimental.test.TestCoroutineContext
import net.iqbalfauzan.mykotlinapp.ApiRepository
import net.iqbalfauzan.mykotlinapp.submission_akhir.ApiService
import net.iqbalfauzan.mykotlinapp.submission_akhir.Model.ModelMatch
import net.iqbalfauzan.mykotlinapp.submission_akhir.Response.NextMatchResponse
import net.iqbalfauzan.mykotlinapp.submission_akhir.View.NextMatchView
import net.iqbalfauzan.mykotlinapp.utils.TestContextProvider
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class NextMatchPresenterTest {
    @Mock
    private
    lateinit var view: NextMatchView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository
    private lateinit var presenter: NextMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = NextMatchPresenter(view, apiRepository, gson, TestContextProvider())
    }


    @Test
    fun getMatchList() {
        val teams: MutableList<ModelMatch> = mutableListOf()
        val response = NextMatchResponse(teams)
        val league = "4328"

        `when`(gson.fromJson(apiRepository
                .doRequest(ApiService.getNextMatch(league)),
                NextMatchResponse::class.java
        )).thenReturn(response)

        presenter.getMatchList(league)

        verify(view).showLoading()
        verify(view).showNextMatchList(teams)
        verify(view).hideLoading()
    }
}