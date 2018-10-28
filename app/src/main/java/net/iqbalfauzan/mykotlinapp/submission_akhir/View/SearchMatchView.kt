package net.iqbalfauzan.mykotlinapp.submission_akhir.View

import net.iqbalfauzan.mykotlinapp.submission_akhir.Model.ModelMatch

interface SearchMatchView {
    fun showLoading()
    fun hideLoading()
    fun showSearchlist(data:List<ModelMatch>)
}