package net.iqbalfauzan.mykotlinapp.submission_akhir

import net.iqbalfauzan.mykotlinapp.ModelTeam

interface TeamView{
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data:List<ModelTeam>)
}