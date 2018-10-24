package net.iqbalfauzan.mykotlinapp.submission_dua.next

import net.iqbalfauzan.mykotlinapp.submission_akhir.Model.ModelMatch


data class NextMatchResponse(
        val events : List<ModelMatch>
)