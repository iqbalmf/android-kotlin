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
import net.iqbalfauzan.mykotlinapp.submission_akhir.Model.ModelTeam
import net.iqbalfauzan.mykotlinapp.R
import net.iqbalfauzan.mykotlinapp.submission_akhir.Adapter.AdapterTeam
import net.iqbalfauzan.mykotlinapp.submission_akhir.TeamActivity
import net.iqbalfauzan.mykotlinapp.submission_akhir.Presenter.TeamPresenter
import net.iqbalfauzan.mykotlinapp.submission_akhir.View.TeamView
import net.iqbalfauzan.mykotlinapp.submission_dua.details.DetailActivity
import net.iqbalfauzan.mykotlinapp.utils.invisible
import net.iqbalfauzan.mykotlinapp.utils.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class Fragment_Teams:Fragment(), AnkoComponent<Context>, AnkoLogger, TeamView {
    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamList(data: List<ModelTeam>) {
        swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

    companion object {

        fun newInstance(): Fragment_Teams {
            return Fragment_Teams()
        }
    }
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    private lateinit var leagueName: String
    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private var teams: MutableList<ModelTeam> = mutableListOf()
    private lateinit var presenter: TeamPresenter
    private lateinit var adapter : AdapterTeam
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
        setHasOptionsMenu(true)
        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter
        adapter = AdapterTeam(teams){
            ctx.startActivity<TeamActivity>("idTeam" to "${it.teamId}")
        }
        listTeam.adapter = adapter
        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamPresenter(this, request, gson)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                leagueName = p0?.selectedItem.toString()
                presenter.getTeamList(leagueName)
                info { p0?.selectedItem.toString() }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
        swipeRefresh.onRefresh {
            presenter.getTeamList(leagueName)
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
        val search = menuItem?.actionView as SearchView
        searching(search)
        super.onPrepareOptionsMenu(menu)
    }
    private fun searching(search: SearchView) {
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.i("TAG","Llego al querysubmit")
                listTeam.adapter = adapter
                val request = ApiRepository()
                val gson = Gson()
                presenter.getSearchTeam(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                Log.i("TAG","Llego al querytextchange")
                listTeam.adapter = adapter
                val request = ApiRepository()
                val gson = Gson()
                presenter.getSearchTeam(newText)
                return true
            }
        })
    }

}