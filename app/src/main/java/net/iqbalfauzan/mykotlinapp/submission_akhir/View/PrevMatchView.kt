package net.iqbalfauzan.mykotlinapp.submission_akhir.View

import net.iqbalfauzan.mykotlinapp.submission_akhir.Model.ModelMatch

interface PrevMatchView{
    fun showLoading()
    fun hideLoading()
    fun showPrevMatch(data: List<ModelMatch>)
}