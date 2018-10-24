package net.iqbalfauzan.mykotlinapp.submission_dua.prev

import com.google.gson.Gson
import net.iqbalfauzan.mykotlinapp.ApiRepository
import net.iqbalfauzan.mykotlinapp.submission_akhir.Model.ModelMatch
import net.iqbalfauzan.mykotlinapp.utils.TestContextProvider
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PrevMatchPresenterTest {
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
        presenter = PrevMatchPresenter(view, gson, apiRepository, TestContextProvider())
    }

    @Test
    fun getPrevMatchList() {
        val teams: MutableList<ModelMatch> = mutableListOf()
        val response = PrevMatchResponse(teams)
        val league = "4328"

        Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(ApiPrevMatch.getTeams(league)),
                PrevMatchResponse::class.java
        )).thenReturn(response)

        presenter.getPrevMatchList(league)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showMatchList(teams)
        Mockito.verify(view).hideLoading()
    }
}