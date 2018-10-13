package net.iqbalfauzan.mykotlinapp.submission_dua.prev

import net.iqbalfauzan.mykotlinapp.submission_dua.prev.ModelPrevMatch

interface PrevMatchView{
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data : List<ModelPrevMatch>)
}