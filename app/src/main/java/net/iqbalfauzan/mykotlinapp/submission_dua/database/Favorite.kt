package net.iqbalfauzan.mykotlinapp.submission_dua.database

data class Favorite(
        val id: Long?,
        val teamId: String?,
        val homeName: String?,
        val idHome: String?,
        val awayName: String?,
        val idAway: String?,
        val dateMatch: String?,
        val homeScore: String?,
        val awayScore: String?) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val MatchID: String = "MatchID"
        const val HOME_NAME: String = "HOME_NAME"
        const val ID_HOME: String = "ID_HOME"
        const val AWAY_NAME: String = "AWAY_NAME"
        const val ID_AWAY: String = "ID_AWAY"
        const val DATE_MATCH: String = "DATE_MATCH"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"
    }
}