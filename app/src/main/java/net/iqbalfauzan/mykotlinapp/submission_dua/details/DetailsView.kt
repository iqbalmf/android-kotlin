package net.iqbalfauzan.mykotlinapp.submission_dua.details

import net.iqbalfauzan.mykotlinapp.submission_dua.prev.ModelPrevMatch

interface DetailsView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data : List<ModelPrevMatch>)
    fun showTeamHome(data : List<ModelTeamHome>)
    fun showTeamAway(data : List<ModelTeamAway>)
}