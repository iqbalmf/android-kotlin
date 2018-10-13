package net.iqbalfauzan.mykotlinapp.submission_dua.fav

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import net.iqbalfauzan.mykotlinapp.R
import net.iqbalfauzan.mykotlinapp.invisible
import net.iqbalfauzan.mykotlinapp.submission_dua.database.Favorite
import net.iqbalfauzan.mykotlinapp.submission_dua.database.database
import net.iqbalfauzan.mykotlinapp.submission_dua.details.DetailActivity
import net.iqbalfauzan.mykotlinapp.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class FavMatchFragment:Fragment(), AnkoComponent<Context>, FavMatchView{
    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showMatchList(data: List<ModelFavorite>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var listFavMatch: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: FavoriteAdapter
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private var favorites: MutableList<Favorite> = mutableListOf()
    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipeRefresh = swipeRefreshLayout {
                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    listFavMatch = recyclerView {
                        id = R.id.list_prev
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    progressBar = progressBar {
                    }.lparams {
                        centerHorizontally()
                    }
                }
            }
        }
    }
    private fun showFavorite(){
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = FavoriteAdapter(requireContext(), favorites){
            ctx.startActivity<DetailActivity>("idMatch" to "${it.teamId}", "idHome" to "${it.idHome}", "idAway" to "${it.idAway}")
        }
        listFavMatch.adapter = adapter
        showFavorite()
        swipeRefresh.onRefresh {
            favorites.clear()
            showFavorite()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

}