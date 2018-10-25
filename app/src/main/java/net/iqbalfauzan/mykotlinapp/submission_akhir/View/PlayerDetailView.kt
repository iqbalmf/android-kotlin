package net.iqbalfauzan.mykotlinapp.submission_akhir.View

import net.iqbalfauzan.mykotlinapp.submission_akhir.Model.ModelDetailPlayer
import net.iqbalfauzan.mykotlinapp.submission_akhir.Model.ModelTeam

interface PlayerDetailView{
    fun showLoading()
    fun hideLoading()
    fun showPlayerDetail(data:List<ModelDetailPlayer>)
}