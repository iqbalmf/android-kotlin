package net.iqbalfauzan.mykotlinapp.submission_akhir.Model

import com.google.gson.annotations.SerializedName

data class ModelTeam(
        @SerializedName("idTeam")
        var teamId: String? = null,
        @SerializedName("strTeam")
        var teamName: String? = null,
        @SerializedName("strTeamBadge")
        var teamBadge: String? = null,
        @SerializedName("strLeague")
        var teamLeague: String? = null,
        @SerializedName("strManager")
        var teamManager: String? = null,
        @SerializedName("strStadium")
        var teamStadium: String? = null,
        @SerializedName("intFormedYear")
        var teamTahun: String? = null,
        @SerializedName("strDescriptionEN")
        var teamDesc: String? = null,
        @SerializedName("strPlayer")
        var namaPlayer: String? = null,
        @SerializedName("strPosition")
        var posisiPlayer: String? = null,
        @SerializedName("strCutout")
        var fotoPlayer: String? = null,
        @SerializedName("idPlayer")
        var idPlayer: String? = null

)