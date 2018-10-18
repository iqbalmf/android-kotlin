package net.iqbalfauzan.mykotlinapp.submission_dua.next

import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import net.iqbalfauzan.mykotlinapp.ApiRepository
import net.iqbalfauzan.mykotlinapp.utils.CoroutineContextProvider
import org.jetbrains.anko.coroutines.experimental.bg

class NextMatchPresenter(private val view: NextMatchView,
                         private val gson: Gson,
                         private val apiRepository: ApiRepository,
                         private val contextPool: CoroutineContextProvider = CoroutineContextProvider()) {
    fun getNextMatchList(nextMatchL: String?) {
        view.showLoading()
       //menggunakan coroutines
        async(contextPool.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(ApiNextMatch.getTeams(nextMatchL)),
                        NextMatchResponse::class.java)
            }
            view.showMatchList(data.await().events)
            view.hideLoading()
        }

        //menggunakan threads
        /* doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(ApiNextMatch.getTeams(prevMatchL)),
                    NextMatchResponse::class.java
            )
            uiThread {
                view.hideLoading()
                view.showMatchList(data.events)
            }
        }*/
    }
}