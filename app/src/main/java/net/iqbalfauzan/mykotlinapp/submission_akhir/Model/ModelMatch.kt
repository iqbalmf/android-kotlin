package net.iqbalfauzan.mykotlinapp.submission_akhir.Model

import com.google.gson.annotations.SerializedName

data class ModelMatch(
                @SerializedName("idEvent")
                var idMatch: String? = null,
                @SerializedName("strEvent")
                var namaEvent: String? = null,
                @SerializedName("strFilename")
                var namaFileEvent: String? = null,
                @SerializedName("intHomeScore")
                var scoreHome: Int? = null,
                @SerializedName("intAwayScore")
                var scoreAway: Int? = null,
                @SerializedName("strHomeTeam")
                var namaHome: String? = null,
                @SerializedName("strAwayTeam")
                var namaAway: String? = null,
                @SerializedName("idHomeTeam")
                var idHome: String? = null,
                @SerializedName("idAwayTeam")
                var idAway: String? = null,
                @SerializedName("dateEvent")
                var tanggal: String? = null,
                @SerializedName("strDate")
                var strDate: String? = null,
                @SerializedName("strTime")
                var jam: String? = null,
                @SerializedName("strHomeGoalDetails")
                var homeGoalDetails: String? = null,
                @SerializedName("strHomeRedCards")
                var homeRed: String? = null,
                @SerializedName("strHomeYellowCards")
                var homeYellow: String? = null,
                @SerializedName("strHomeLineupGoalkeeper")
                var HomeLineupGoalkeeper: String? = null,
                @SerializedName("strHomeLineupDefense")
                var HomeLineupDefense: String? = null,
                @SerializedName("strAwayLineupDefense")
                var AwayLineupDefense: String? = null,
                @SerializedName("strHomeLineupMidfield")
                var HomeLineupMidfield: String? = null,
                @SerializedName("strHomeLineupForward")
                var HomeLineupForward: String? = null,
                @SerializedName("strHomeLineupSubstitutes")
                var HomeLineupSubstitutes: String? = null,
                @SerializedName("strAwayRedCards")
                var AwayRedCards: String? = null,
                @SerializedName("strAwayYellowCards")
                var AwayYellowCards: String? = null,
                @SerializedName("strAwayGoalDetails")
                var AwayGoalDetails: String? = null,
                @SerializedName("strAwayLineupGoalkeeper")
                var AwayLineupGoalkeeper: String? = null,
                @SerializedName("strAwayLineupMidfield")
                var AwayLineupMidfield: String? = null,
                @SerializedName("strAwayLineupForward")
                var AwayLineupForward: String? = null,
                @SerializedName("strAwayLineupSubstitutes")
                var AwayLineupSubstitutes: String? = null,
                @SerializedName("strHomeFormation")
                var HomeFormation: String? = null,
                @SerializedName("strAwayFormation")
                var AwayFormation: String? = null,
                @SerializedName("intHomeShots")
                var homeShots: Int? = null,
                @SerializedName("intAwayShots")
                var awayShots: Int? = null

)