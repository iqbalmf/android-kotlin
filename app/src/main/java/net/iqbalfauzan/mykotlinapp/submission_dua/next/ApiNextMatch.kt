package net.iqbalfauzan.mykotlinapp.submission_dua.next

import android.net.Uri
import net.iqbalfauzan.mykotlinapp.BuildConfig

object ApiNextMatch {
    fun getTeams(idPrevMatch: String?):String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventsnextleague.php?id=" + idPrevMatch
    }
    //BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/search_all_teams.php?l=" + league
}