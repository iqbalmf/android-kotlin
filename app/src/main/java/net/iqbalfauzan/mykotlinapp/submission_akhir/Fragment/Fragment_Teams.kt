package net.iqbalfauzan.mykotlinapp.submission_akhir.Fragment

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Spinner
import net.iqbalfauzan.mykotlinapp.R
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class Fragment_Teams:Fragment(), AnkoComponent<Context>{
    private lateinit var spinner: Spinner
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var listTeams: RecyclerView
    private lateinit var progressBar: ProgressBar
    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)
            spinner = spinner()
            swipeRefresh = swipeRefreshLayout {
                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    listTeams = recyclerView {
                        id = R.id.list_fav
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

}