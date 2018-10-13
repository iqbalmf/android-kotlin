package net.iqbalfauzan.mykotlinapp.submission_dua.details

import com.google.gson.Gson
import net.iqbalfauzan.mykotlinapp.ApiRepository
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailsPresenter(private val view: DetailsView,
                         private val gson: Gson,
                         private val apiRepository: ApiRepository) {

    fun getEventDetails(eventLookUp: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(ApiDetails.getEventLookUp(eventLookUp)),
                    DetailsResponse::class.java
            )
            uiThread {
                view.hideLoading()
                view.showMatchList(data.events)
            }
        }
    }
    fun getBadgeDetailsHome(logo: String?) {
        view.showLoading()
        doAsync {
            val dataHome = gson.fromJson(apiRepository
                    .doRequest(ApiDetails.getLogoTeamHome(logo)),
                    HomeResponse::class.java
            )
            uiThread {
                view.hideLoading()
                view.showTeamHome(dataHome.teams)
            }
        }
    }
    fun getBadgeDetailsAway(logo: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(ApiDetails.getLogoTeamAway(logo)),
                    AwayResponse::class.java
            )
            uiThread {
                view.hideLoading()
                view.showTeamAway(data.teams)
            }
        }
    }
}