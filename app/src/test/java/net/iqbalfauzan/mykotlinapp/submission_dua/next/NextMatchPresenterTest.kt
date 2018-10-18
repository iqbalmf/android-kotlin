package net.iqbalfauzan.mykotlinapp.submission_dua.next

import com.google.gson.Gson
import net.iqbalfauzan.mykotlinapp.ApiRepository
import net.iqbalfauzan.mykotlinapp.submission_dua.prev.ModelPrevMatch
import net.iqbalfauzan.mykotlinapp.utils.TestContextProvider
import org.junit.Test

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
        presenter = NextMatchPresenter(view, gson, apiRepository, TestContextProvider())
    }

    @Test
    fun getNextMatchList() {
        val teams: MutableList<ModelPrevMatch> = mutableListOf()
        val response = NextMatchResponse(teams)
        val league = "4328"

        `when`(gson.fromJson(apiRepository
                .doRequest(ApiNextMatch.getTeams(league)),
                NextMatchResponse::class.java
        )).thenReturn(response)

        presenter.getNextMatchList(league)

        verify(view).showLoading()
        verify(view).showMatchList(teams)
        verify(view).hideLoading()

    }
}