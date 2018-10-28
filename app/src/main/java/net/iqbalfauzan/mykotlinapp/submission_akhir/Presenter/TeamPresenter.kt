package net.iqbalfauzan.mykotlinapp.submission_akhir.Presenter

import com.google.gson.Gson
import net.iqbalfauzan.mykotlinapp.ApiRepository
import net.iqbalfauzan.mykotlinapp.submission_akhir.ApiService
import net.iqbalfauzan.mykotlinapp.submission_akhir.Response.PlayerResponse
import net.iqbalfauzan.mykotlinapp.submission_akhir.Response.TeamResponse
import net.iqbalfauzan.mykotlinapp.submission_akhir.View.TeamView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamPresenter(
        private val view: TeamView,
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
    fun getSearchTeam(idTeam: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(ApiService.searchTeam(idTeam)),
                    TeamResponse::class.java
            )

            uiThread {
                view.hideLoading()
                if (data.teams != null) {
                    view.showTeamList(data.teams)
                }
            }
        }
    }
    fun getTeam(idTeam: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(ApiService.getTeam(idTeam)),
                    TeamResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showTeamList(data.teams)
            }
        }
    }
    fun getPlayer(idTeam: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(ApiService.getPlayerList(idTeam)),
                    PlayerResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showTeamList(data.player)
            }
        }
    }
}