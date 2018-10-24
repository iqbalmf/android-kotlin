package net.iqbalfauzan.mykotlinapp.submission_akhir.Presenter

import com.google.gson.Gson
import net.iqbalfauzan.mykotlinapp.ApiRepository
import net.iqbalfauzan.mykotlinapp.submission_akhir.ApiService
import net.iqbalfauzan.mykotlinapp.submission_akhir.Response.NextMatchResponse
import net.iqbalfauzan.mykotlinapp.submission_akhir.View.NextMatchView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class NextMatchPresenter(private val view:NextMatchView, private val apiRepository: ApiRepository,
                         private val gson:Gson){
    fun getMatchList(leagueID: String?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(ApiService.getNextMatch(leagueID)),
                    NextMatchResponse::class.java)
            uiThread {
                view.hideLoading()
                view.showNextMatchList(data.events)
            }
        }
    }
}