package net.iqbalfauzan.mykotlinapp.submission_dua.details

import net.iqbalfauzan.mykotlinapp.submission_akhir.Model.ModelMatch

interface DetailsView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data : List<ModelMatch>)
    fun showTeamHome(data : List<ModelTeamHome>)
    fun showTeamAway(data : List<ModelTeamAway>)
}