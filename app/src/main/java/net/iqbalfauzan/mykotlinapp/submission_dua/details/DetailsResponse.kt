package net.iqbalfauzan.mykotlinapp.submission_dua.details

import net.iqbalfauzan.mykotlinapp.submission_dua.prev.ModelPrevMatch


data class DetailsResponse (
    val events : List<ModelPrevMatch>
)