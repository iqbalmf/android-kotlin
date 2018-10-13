package net.iqbalfauzan.mykotlinapp.submission_dua.details

import android.net.Uri
import net.iqbalfauzan.mykotlinapp.BuildConfig

object ApiDetails {
    fun getEventLookUp(idEvent: String?): String{
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
    fun getLogoTeamHome(logoHome: String?): String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("lookupteam.php")
                .appendQueryParameter("id", logoHome)
                .build()
                .toString()
    }
    fun getLogoTeamAway(logoAway: String?): String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("lookupteam.php")
                .appendQueryParameter("id", logoAway)
                .build()
                .toString()
    }
}