package net.iqbalfauzan.mykotlinapp.submission_akhir.Fragment

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
import net.iqbalfauzan.mykotlinapp.submission_akhir.Adapter.AdapterTeamFavorite
import net.iqbalfauzan.mykotlinapp.submission_akhir.TeamActivity
import net.iqbalfauzan.mykotlinapp.submission_akhir.database.TeamFavorite
import net.iqbalfauzan.mykotlinapp.submission_akhir.database.databaseTeam
import net.iqbalfauzan.mykotlinapp.utils.invisible
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class Fragment_TeamFav:Fragment(), AnkoComponent<Context>{
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private var teams: MutableList<TeamFavorite> = mutableListOf()
    private lateinit var adapter : AdapterTeamFavorite
    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = matchParent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)
            swipeRefresh = swipeRefreshLayout {
                relativeLayout{
                    lparams (width = matchParent, height = wrapContent)

                    listTeam = recyclerView {
                        lparams (width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    progressBar = progressBar {
                    }.lparams{
                        centerHorizontally()
                    }
                }
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = AdapterTeamFavorite(teams){
            ctx.startActivity<TeamActivity>("idTeam" to "${it.teamId}")
        }
        listTeam.adapter = adapter
        showFavorite()
        swipeRefresh.onRefresh {
            teams.clear()
            showFavorite()
        }
    }
    private fun showFavorite(){
        context?.databaseTeam?.use {
            swipeRefresh.isRefreshing = false
            progressBar.invisible()
            val result = select(TeamFavorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<TeamFavorite>())
            teams.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.Companion.create(ctx))
    }
}