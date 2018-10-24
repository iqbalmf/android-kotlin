package net.iqbalfauzan.mykotlinapp.submission_akhir.View

import net.iqbalfauzan.mykotlinapp.submission_akhir.Model.ModelTeam

interface TeamView{
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data:List<ModelTeam>)
}