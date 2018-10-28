package net.iqbalfauzan.mykotlinapp.submission_akhir.Fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import android.widget.*
import com.google.gson.Gson
import net.iqbalfauzan.mykotlinapp.ApiRepository
import net.iqbalfauzan.mykotlinapp.R
import net.iqbalfauzan.mykotlinapp.submission_akhir.Adapter.AdapterPrevMatch
import net.iqbalfauzan.mykotlinapp.submission_akhir.Model.ModelMatch
import net.iqbalfauzan.mykotlinapp.submission_akhir.Presenter.PrevMatchPresenter
import net.iqbalfauzan.mykotlinapp.submission_akhir.Presenter.SearchMatchPresenter
import net.iqbalfauzan.mykotlinapp.submission_akhir.View.PrevMatchView
import net.iqbalfauzan.mykotlinapp.submission_akhir.View.SearchMatchView
import net.iqbalfauzan.mykotlinapp.submission_dua.details.DetailActivity
import net.iqbalfauzan.mykotlinapp.utils.invisible
import net.iqbalfauzan.mykotlinapp.utils.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class Fragment_Prev:Fragment(), AnkoComponent<Context>, AnkoLogger, PrevMatchView, SearchMatchView{
    override fun showSearchlist(data: List<ModelMatch>) {
        swipeRefresh.isRefreshing = false
        prevMatch.clear()
        prevMatch.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showPrevMatch(data: List<ModelMatch>) {
        swipeRefresh.isRefreshing = false
        prevMatch.clear()
        prevMatch.addAll(data)
        adapter.notifyDataSetChanged()
    }

    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    private lateinit var listPrevMatch: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var leagueID :String
    private var prevMatch : MutableList<ModelMatch> = mutableListOf()
    private lateinit var presenter:PrevMatchPresenter
    private lateinit var presenterSearch: SearchMatchPresenter
    private lateinit var adapter: AdapterPrevMatch
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

                    listPrevMatch = recyclerView {
                        id = R.id.list_prev
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
        setHasOptionsMenu(true)
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
        adapter = AdapterPrevMatch(requireContext(), prevMatch){
            ctx.startActivity<DetailActivity>("idMatch" to "${it.idMatch}", "idHome" to "${it.idHome}", "idAway" to "${it.idAway}")

        }
        listPrevMatch.adapter = adapter
        val request = ApiRepository()
        val gson = Gson()
        presenter = PrevMatchPresenter(this, request, gson)
        presenterSearch = SearchMatchPresenter(this, request, gson)
        swipeRefresh.onRefresh {
            presenter.getMatchList(leagueID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_search, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        val menuItem = menu?.findItem(R.id.action_search)
        val search = menuItem?.actionView as android.widget.SearchView
        searching(search)
        super.onPrepareOptionsMenu(menu)
    }
    fun searching(search: SearchView) {
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.i("TAG","Llego al querysubmit")

                listPrevMatch.adapter = adapter
                presenterSearch.getMatchList(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                Log.i("TAG","Llego al querytextchange")

                listPrevMatch.adapter = adapter
                presenterSearch.getMatchList(newText)
                return true
            }
        })
    }
}