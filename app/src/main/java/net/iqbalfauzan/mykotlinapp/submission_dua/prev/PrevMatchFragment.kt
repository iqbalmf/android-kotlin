package net.iqbalfauzan.mykotlinapp.submission_dua.prev

import android.content.Context
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.gson.Gson
import net.iqbalfauzan.mykotlinapp.R
import net.iqbalfauzan.mykotlinapp.submission_dua.details.DetailActivity
import net.iqbalfauzan.mykotlinapp.ApiRepository
import net.iqbalfauzan.mykotlinapp.submission_akhir.Model.ModelMatch
import net.iqbalfauzan.mykotlinapp.utils.invisible
import net.iqbalfauzan.mykotlinapp.utils.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class PrevMatchFragment:android.support.v4.app.Fragment(),
        AnkoComponent<Context>, PrevMatchView {
    private var prevMatchs: MutableList<ModelMatch> = mutableListOf()
    private lateinit var presenter: PrevMatchPresenter
    private lateinit var prevAdapter: PrevMatchAdapter
    private lateinit var spinner: Spinner
    private lateinit var listPrevMatch: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var namaLiga: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {

        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipeRefreshLayout = swipeRefreshLayout {
                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    listPrevMatch = recyclerView {
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
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        prevAdapter = PrevMatchAdapter(requireContext(), prevMatchs){
            ctx.startActivity<DetailActivity>("idMatch" to "${it.idMatch}", "idHome" to "${it.idHome}", "idAway" to "${it.idAway}")
        }
        listPrevMatch.adapter = prevAdapter
        val request = ApiRepository()
        val gson = Gson()
        presenter = PrevMatchPresenter(this, gson, request)
        presenter.getPrevMatchList("4328")

        swipeRefreshLayout.onRefresh {
            presenter.getPrevMatchList("4328")
        }
    }
    override fun showMatchList(data: List<ModelMatch>) {
        swipeRefreshLayout.isRefreshing = false
        prevMatchs.clear()
        prevMatchs.addAll(data)
        prevAdapter.notifyDataSetChanged()
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

}