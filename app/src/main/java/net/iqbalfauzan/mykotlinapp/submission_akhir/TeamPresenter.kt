package net.iqbalfauzan.mykotlinapp.submission_akhir

import android.view.View
import com.google.gson.Gson
import net.iqbalfauzan.mykotlinapp.ApiRepository
import net.iqbalfauzan.mykotlinapp.submission_akhir.Response.TeamResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamPresenter(
        private val view:TeamView,
        private val apiRepository: ApiRepository,
        private val gson:Gson)
{
    fun getTeamList(league: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(ApiService.getTeams(league)),
                    TeamResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showTeamList(data.teams)
            }
        }
    }
}