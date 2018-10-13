package net.iqbalfauzan.mykotlinapp

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
        var teamStadium: String? = null
)