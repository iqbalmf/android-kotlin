package net.iqbalfauzan.mykotlinapp.submission_dua.prev

import net.iqbalfauzan.mykotlinapp.submission_akhir.Model.ModelMatch

interface PrevMatchView{
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data : List<ModelMatch>)
}