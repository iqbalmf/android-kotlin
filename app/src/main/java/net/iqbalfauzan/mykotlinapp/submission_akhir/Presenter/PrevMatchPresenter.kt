package net.iqbalfauzan.mykotlinapp.submission_akhir.Presenter

import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import net.iqbalfauzan.mykotlinapp.ApiRepository
import net.iqbalfauzan.mykotlinapp.submission_akhir.ApiService
import net.iqbalfauzan.mykotlinapp.submission_akhir.Response.PrevMatchResponse
import net.iqbalfauzan.mykotlinapp.submission_akhir.View.PrevMatchView
import net.iqbalfauzan.mykotlinapp.utils.CoroutineContextProvider
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PrevMatchPresenter(private val view:PrevMatchView,
                         private val apiRepository: ApiRepository,
                         private val gson:Gson,
                         private val context: CoroutineContextProvider = CoroutineContextProvider()){
    fun getMatchList(leagueID:String?){
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(ApiService.getPrevMatch(leagueID)),
                        PrevMatchResponse::class.java)
            }
                    view.hideLoading()
                    view.showPrevMatch(data.await().events)
        }
    }
}