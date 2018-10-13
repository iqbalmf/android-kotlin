package net.iqbalfauzan.mykotlinapp.submission_dua.details

import net.iqbalfauzan.mykotlinapp.ModelTeam

interface TeamDetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<ModelTeam>)
}