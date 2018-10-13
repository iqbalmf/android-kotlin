package net.iqbalfauzan.mykotlinapp.submission_dua.next

import net.iqbalfauzan.mykotlinapp.submission_dua.prev.ModelPrevMatch


data class NextMatchResponse(
        val events : List<ModelPrevMatch>
)