package net.iqbalfauzan.mykotlinapp.submission_dua.details

import net.iqbalfauzan.mykotlinapp.submission_akhir.Model.ModelMatch


data class DetailsResponse (
    val events : List<ModelMatch>
)