package net.iqbalfauzan.mykotlinapp.submission_akhir.Response

import net.iqbalfauzan.mykotlinapp.submission_akhir.Model.ModelMatch

data class SearchMatchResponse(
        val event: List<ModelMatch>
)