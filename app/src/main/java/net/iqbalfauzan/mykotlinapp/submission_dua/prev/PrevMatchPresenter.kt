package net.iqbalfauzan.mykotlinapp.submission_dua.prev

import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import net.iqbalfauzan.mykotlinapp.ApiRepository
import net.iqbalfauzan.mykotlinapp.utils.CoroutineContextProvider
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PrevMatchPresenter(private val view: PrevMatchView,
                         private val gson: Gson,
                         private val apiRepository: ApiRepository,
                         private val context: CoroutineContextProvider = CoroutineContextProvider()){
    fun getPrevMatchList(prevMatchL : String?){
        view.showLoading()
        //menggunakan coroutines
        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(ApiPrevMatch.getTeams(prevMatchL)),
                        PrevMatchResponse::class.java)
            }
            view.showMatchList(data.await().events)
            view.hideLoading()
        }
        //mengggunakan thread
        /*doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(ApiPrevMatch.getTeams(prevMatchL)),
                    PrevMatchResponse::class.java
            )
            uiThread {
                view.hideLoading()
                view.showMatchList(data.events)
            }
        }*/
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