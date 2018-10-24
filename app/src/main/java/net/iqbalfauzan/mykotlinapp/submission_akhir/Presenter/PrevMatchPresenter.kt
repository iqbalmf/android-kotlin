package net.iqbalfauzan.mykotlinapp.submission_akhir.Presenter

import com.google.gson.Gson
import net.iqbalfauzan.mykotlinapp.ApiRepository
import net.iqbalfauzan.mykotlinapp.submission_akhir.ApiService
import net.iqbalfauzan.mykotlinapp.submission_akhir.Response.PrevMatchResponse
import net.iqbalfauzan.mykotlinapp.submission_akhir.View.PrevMatchView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PrevMatchPresenter(private val view:PrevMatchView,
                         private val apiRepository: ApiRepository,
                         private val gson:Gson){
    fun getMatchList(leagueID:String?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(ApiService.getPrevMatch(leagueID)),
                    PrevMatchResponse::class.java)
            uiThread {
                view.hideLoading()
                view.showPrevMatch(data.events)
            }
        }
    }
}