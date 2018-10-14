package net.iqbalfauzan.mykotlinapp.submission_dua.prev

import android.net.Uri
import net.iqbalfauzan.mykotlinapp.BuildConfig

object ApiPrevMatch{
    fun getTeams(idPrevMatch: String?):String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventspastleague.php?id=" + idPrevMatch

    }
    fun getDetails(idEvent: String?) :String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("lookupevent.php")
                .appendQueryParameter("id", idEvent)
                .build()
                .toString()
    }
}