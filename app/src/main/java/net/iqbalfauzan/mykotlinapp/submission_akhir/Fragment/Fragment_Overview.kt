package net.iqbalfauzan.mykotlinapp.submission_akhir.Fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.google.gson.Gson
import net.iqbalfauzan.mykotlinapp.ApiRepository
import net.iqbalfauzan.mykotlinapp.R
import net.iqbalfauzan.mykotlinapp.submission_akhir.Model.ModelTeam
import net.iqbalfauzan.mykotlinapp.submission_akhir.Presenter.TeamPresenter
import net.iqbalfauzan.mykotlinapp.submission_akhir.View.TeamView
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.ctx

class Fragment_Overview: Fragment(), AnkoComponent<Context>, AnkoLogger, TeamView{
    private lateinit var textOverview: TextView
    private lateinit var presenter: TeamPresenter
    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showTeamList(data: List<ModelTeam>) {
        textOverview.text = data.get(0).teamDesc
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        scrollView {
            linearLayout {
                lparams(matchParent, matchParent)
                orientation = LinearLayout.VERTICAL
                topPadding = dip(16)
                leftPadding = dip(16)
                rightPadding = dip(16)
                textOverview = textView {
                    id = R.id.textOverview
                    textSize = 16.0F
                }
            }
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val args = arguments
        val idTeam = args?.getString("idTeam")
        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamPresenter(this, request, gson)
        presenter.getTeam(idTeam)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.Companion.create(ctx))
    }

}