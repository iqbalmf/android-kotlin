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
import android.widget.*
import com.google.gson.Gson
import net.iqbalfauzan.mykotlinapp.ApiRepository
import net.iqbalfauzan.mykotlinapp.R
import net.iqbalfauzan.mykotlinapp.submission_akhir.Adapter.AdapterNextMatch
import net.iqbalfauzan.mykotlinapp.submission_akhir.Model.ModelMatch
import net.iqbalfauzan.mykotlinapp.submission_akhir.Presenter.NextMatchPresenter
import net.iqbalfauzan.mykotlinapp.submission_akhir.View.NextMatchView
import net.iqbalfauzan.mykotlinapp.submission_dua.next.NextMatchAdapter
import net.iqbalfauzan.mykotlinapp.utils.invisible
import net.iqbalfauzan.mykotlinapp.utils.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class Fragment_Next:Fragment(), AnkoComponent<Context>, AnkoLogger, NextMatchView{
    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showNextMatchList(data: List<ModelMatch>) {
        swipeRefresh.isRefreshing = false
        nextMatch.clear()
        nextMatch.addAll(data)
        adapter.notifyDataSetChanged()
    }

    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    private lateinit var leagueName: String
    private lateinit var listNext: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var leagueID: String
    private var nextMatch : MutableList<ModelMatch> = mutableListOf()
    private lateinit var presenter: NextMatchPresenter
    private lateinit var adapter: AdapterNextMatch
    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = matchParent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)
            spinner = spinner()
            swipeRefresh = swipeRefreshLayout {
                relativeLayout{
                    lparams (width = matchParent, height = wrapContent)

                    listNext = recyclerView {
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
        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p0?.selectedItemPosition == 0){
                    leagueID = "4328"
                    presenter.getMatchList(leagueID)
                    info { leagueID }
                }else if (p0?.selectedItemPosition == 1){
                    leagueID = "4329"
                    presenter.getMatchList(leagueID)
                    info { leagueID }
                }else if (p0?.selectedItemPosition == 2){
                    leagueID = "4331"
                    presenter.getMatchList(leagueID)
                    info { leagueID }
                }else if (p0?.selectedItemPosition == 3){
                    leagueID = "4332"
                    presenter.getMatchList(leagueID)
                    info { leagueID }
                }else if (p0?.selectedItemPosition == 4){
                    leagueID = "4334"
                    presenter.getMatchList(leagueID)
                    info { leagueID }
                }else if (p0?.selectedItemPosition == 5){
                    leagueID = "4335"
                    presenter.getMatchList(leagueID)
                    info { leagueID }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
        adapter = AdapterNextMatch(requireContext(), nextMatch){

        }
        listNext.adapter = adapter
        val request = ApiRepository()
        val gson = Gson()
        presenter = NextMatchPresenter(this, request, gson)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }
}