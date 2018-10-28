package net.iqbalfauzan.mykotlinapp.submission_dua.fav

import com.google.gson.annotations.SerializedName

data class ModelFavorite(
        @SerializedName("idEvent")
        var idMatch: String? = null,
        @SerializedName("intHomeScore")
        var scoreHome: String? = null,
        @SerializedName("intAwayScore")
        var scoreAway: String? = null,
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
        @SerializedName("strTime")
        var jam: String? = null
)