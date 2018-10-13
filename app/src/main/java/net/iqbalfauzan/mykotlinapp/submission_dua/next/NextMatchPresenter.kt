package net.iqbalfauzan.mykotlinapp.submission_dua.next

import com.google.gson.Gson
import net.iqbalfauzan.mykotlinapp.ApiRepository
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class NextMatchPresenter(private val view: NextMatchView,
                         private val gson: Gson,
                         private val apiRepository: ApiRepository) {
    fun getNextMatchList(prevMatchL: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(ApiNextMatch.getTeams(prevMatchL)),
                    NextMatchResponse::class.java
            )
            uiThread {
                view.hideLoading()
                view.showMatchList(data.events)
            }
        }
    }
}