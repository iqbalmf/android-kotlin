package net.iqbalfauzan.mykotlinapp.submission_dua.next

import net.iqbalfauzan.mykotlinapp.submission_akhir.Model.ModelMatch

interface NextMatchView{
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data : List<ModelMatch>)
}