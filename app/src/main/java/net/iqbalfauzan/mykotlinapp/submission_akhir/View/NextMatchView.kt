package net.iqbalfauzan.mykotlinapp.submission_akhir.View

import net.iqbalfauzan.mykotlinapp.submission_akhir.Model.ModelMatch

interface NextMatchView{
    fun showLoading()
    fun hideLoading()
    fun showNextMatchList(data:List<ModelMatch>)
}