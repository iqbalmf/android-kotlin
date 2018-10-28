package net.iqbalfauzan.mykotlinapp.submission_akhir.Presenter

import com.google.gson.Gson
import net.iqbalfauzan.mykotlinapp.ApiRepository
import net.iqbalfauzan.mykotlinapp.submission_akhir.ApiService
import net.iqbalfauzan.mykotlinapp.submission_akhir.Response.SearchMatchResponse
import net.iqbalfauzan.mykotlinapp.submission_akhir.View.SearchMatchView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class SearchMatchPresenter(private val view: SearchMatchView, private val apiRepository: ApiRepository,
                         private val gson: Gson){
    fun getMatchList(matchs: String?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(ApiService.searchMatch(matchs)),
                    SearchMatchResponse::class.java)
            uiThread {
                view.hideLoading()
                if (data.event != null) {
                    view.showSearchlist(data.event)
                }
            }
        }
    }
}