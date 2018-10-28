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
    fun getNextMatch(idLeage:String?) : String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("eventsnextleague.php")
                .appendQueryParameter("id", idLeage)
                .build()
                .toString()
    }
    fun getPrevMatch(idLeage:String?) : String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("eventspastleague.php")
                .appendQueryParameter("id", idLeage)
                .build()
                .toString()
    }
    fun getTeam(idTeam:String?) : String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("lookupteam.php")
                .appendQueryParameter("id", idTeam)
                .build()
                .toString()
    }
    fun getPlayerList(idTeam:String?) : String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("lookup_all_players.php")
                .appendQueryParameter("id", idTeam)
                .build()
                .toString()
    }
    fun getPlayer(idPlayer:String?) : String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("lookupplayer.php")
                .appendQueryParameter("id", idPlayer)
                .build()
                .toString()
    }
    fun searchTeam(teams:String?) : String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("searchteams.php")
                .appendQueryParameter("t", teams)
                .build()
                .toString()
    }
    fun searchMatch(matchs:String?) : String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("searchevents.php")
                .appendQueryParameter("e", matchs)
                .build()
                .toString()
    }
}