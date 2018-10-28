package net.iqbalfauzan.mykotlinapp.submission_akhir.Presenter

import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import net.iqbalfauzan.mykotlinapp.ApiRepository
import net.iqbalfauzan.mykotlinapp.submission_akhir.ApiService
import net.iqbalfauzan.mykotlinapp.submission_akhir.Response.NextMatchResponse
import net.iqbalfauzan.mykotlinapp.submission_akhir.View.NextMatchView
import net.iqbalfauzan.mykotlinapp.utils.CoroutineContextProvider
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class NextMatchPresenter(private val view:NextMatchView, private val apiRepository: ApiRepository,
                         private val gson:Gson,
                         private val context: CoroutineContextProvider = CoroutineContextProvider()){
    fun getMatchList(leagueID: String?){
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(ApiService.getNextMatch(leagueID)),
                        NextMatchResponse::class.java)
            }

            view.hideLoading()
            view.showNextMatchList(data.await().events)
        }
    }
}