package net.iqbalfauzan.mykotlinapp.submission_akhir.Presenter

import com.google.gson.Gson
import net.iqbalfauzan.mykotlinapp.ApiRepository
import net.iqbalfauzan.mykotlinapp.submission_akhir.ApiService
import net.iqbalfauzan.mykotlinapp.submission_akhir.Response.PlayerDetailResponse
import net.iqbalfauzan.mykotlinapp.submission_akhir.Response.TeamResponse
import net.iqbalfauzan.mykotlinapp.submission_akhir.View.PlayerDetailView
import net.iqbalfauzan.mykotlinapp.submission_akhir.View.TeamView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PlayerDetailPresenter(
        private val view: PlayerDetailView,
        private val apiRepository: ApiRepository,
        private val gson: Gson
){
    fun getPlayerDetail(idPlayer: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(ApiService.getPlayer(idPlayer)),
                    PlayerDetailResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showPlayerDetail(data.players)
            }
        }
    }
}