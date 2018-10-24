package net.iqbalfauzan.mykotlinapp.submission_dua.fav

interface FavMatchView{
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data : List<ModelFavorite>)
}