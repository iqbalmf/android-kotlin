package net.iqbalfauzan.mykotlinapp.submission_dua.next

import net.iqbalfauzan.mykotlinapp.submission_dua.prev.ModelPrevMatch

interface NextMatchView{
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data : List<ModelPrevMatch>)
}