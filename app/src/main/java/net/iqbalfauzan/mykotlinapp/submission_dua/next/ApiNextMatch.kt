package net.iqbalfauzan.mykotlinapp.submission_dua.next

import android.net.Uri
import net.iqbalfauzan.mykotlinapp.BuildConfig

object ApiNextMatch {
    fun getTeams(idPrevMatch: String?):String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("eventsnextleague.php")
                .appendQueryParameter("id", idPrevMatch)
                .build()
                .toString()
    }
}