package net.iqbalfauzan.mykotlinapp.submission_akhir

import android.net.Uri
import net.iqbalfauzan.mykotlinapp.BuildConfig

object ApiService{
    fun getTeams(league: String?) :String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("search_all_teams.php")
                .appendQueryParameter("l", league)
                .build()
                .toString()
    }
}