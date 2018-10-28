package net.iqbalfauzan.mykotlinapp.submission_akhir.database

data class TeamFavorite(
        val id: Long?,
        val teamId: String?,
        val teamImage : String?,
        val teamName: String) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_TEAM_FAVORITE"
        const val ID: String = "ID_"
        const val TEAMID: String = "TEAMID"
        const val TEAMIMAGE: String = "TEAMIMAGE"
        const val TEAMNAME: String = "TEAMNAME"
    }
}