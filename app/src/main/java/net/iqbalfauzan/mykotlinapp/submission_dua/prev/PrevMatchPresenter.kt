package net.iqbalfauzan.mykotlinapp.submission_dua.prev

import com.google.gson.Gson
import net.iqbalfauzan.mykotlinapp.ApiRepository
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PrevMatchPresenter(private val view: PrevMatchView,
                         private val gson: Gson,
                         private val apiRepository: ApiRepository){
    fun getPrevMatchList(prevMatchL : String?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(ApiPrevMatch.getTeams(prevMatchL)),
                    PrevMatchResponse::class.java
            )
            uiThread {
                view.hideLoading()
                view.showMatchList(data.events)
            }
        }
    }
    fun getMatchDetails(matchDetails : String ?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(ApiPrevMatch.getDetails(matchDetails)),
                    PrevMatchResponse::class.java
            )
            uiThread {
                view.hideLoading()
                view.showMatchList(data.events)
            }
        }
    }
}