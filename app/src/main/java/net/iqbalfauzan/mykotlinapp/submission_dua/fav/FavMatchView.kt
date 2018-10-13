package net.iqbalfauzan.mykotlinapp.submission_dua.fav

import net.iqbalfauzan.mykotlinapp.submission_dua.prev.ModelPrevMatch

interface FavMatchView{
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data : List<ModelFavorite>)
}