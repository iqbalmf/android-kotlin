package net.iqbalfauzan.mykotlinapp.submission_dua.details

import com.google.gson.annotations.SerializedName

data class ModelTeamAway(
        @SerializedName("strTeamBadge")
        var teamBadge: String? = null
)