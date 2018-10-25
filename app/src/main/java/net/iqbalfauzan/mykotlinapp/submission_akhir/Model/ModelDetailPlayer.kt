package net.iqbalfauzan.mykotlinapp.submission_akhir.Model

import com.google.gson.annotations.SerializedName

data class ModelDetailPlayer(
        @SerializedName("idPlayer")
        var idPlayer: String? = null,
        @SerializedName("strPlayer")
        var strPlayer: String? = null,
        @SerializedName("strPosition")
        var strPosition: String? = null,
        @SerializedName("strDescriptionEN")
        var strDescriptionEN: String? = null,
        @SerializedName("strHeight")
        var strHeight: String? = null,
        @SerializedName("strWeight")
        var strWeight: String? = null,
        @SerializedName("strFanart1")
        var strFanart1: String? = null
)