package net.iqbalfauzan.mykotlinapp.submission_akhir.Fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Spinner
import com.google.gson.Gson
import net.iqbalfauzan.mykotlinapp.ApiRepository
import net.iqbalfauzan.mykotlinapp.submission_akhir.Adapter.AdapterPlayer
import net.iqbalfauzan.mykotlinapp.submission_akhir.Adapter.AdapterTeam
import net.iqbalfauzan.mykotlinapp.submission_akhir.Model.ModelMatch
import net.iqbalfauzan.mykotlinapp.submission_akhir.Model.ModelTeam
import net.iqbalfauzan.mykotlinapp.submission_akhir.PlayerDetailActivity
import net.iqbalfauzan.mykotlinapp.submission_akhir.Presenter.TeamPresenter
import net.iqbalfauzan.mykotlinapp.submission_akhir.TeamActivity
import net.iqbalfauzan.mykotlinapp.submission_akhir.View.TeamView
import net.iqbalfauzan.mykotlinapp.submission_dua.details.DetailActivity
import net.iqbalfauzan.mykotlinapp.utils.invisible
import net.iqbalfauzan.mykotlinapp.utils.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class Fragment_Players: Fragment(), AnkoComponent<Context>, AnkoLogger, TeamView{
    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamList(data: List<ModelTeam>) {
        players.clear()
        players.addAll(data)
        adapter.notifyDataSetChanged()
    }

    private lateinit var listPlayer: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter:TeamPresenter
    private var players : MutableList<ModelTeam> = mutableListOf()
    private lateinit var adapter:AdapterPlayer
    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        linearLayout {
            lparams(width = matchParent, height = matchParent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)
            relativeLayout {
                lparams(width = matchParent, height = wrapContent)

                listPlayer = recyclerView {
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = AdapterPlayer(players){
            ctx.startActivity<PlayerDetailActivity>("idPlayer" to "${it.idPlayer}")
        }
        listPlayer.adapter = adapter
        val args = arguments
        val idTeam = args?.getString("idTeam")
        val request = ApiRepository()
        val gson = Gson()
        info { "ID TEAM"+idTeam }
        presenter = TeamPresenter(this, request, gson)
        presenter.getPlayer(idTeam)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.Companion.create(ctx))
    }

}