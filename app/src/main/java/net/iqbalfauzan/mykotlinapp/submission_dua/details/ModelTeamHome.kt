package net.iqbalfauzan.mykotlinapp.submission_dua.details

import com.google.gson.annotations.SerializedName

data class ModelTeamHome(
        @SerializedName("strTeamBadge")
        var teamBadge: String? = null
)