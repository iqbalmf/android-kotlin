package net.iqbalfauzan.mykotlinapp.submission_akhir

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import net.iqbalfauzan.mykotlinapp.ApiRepository
import net.iqbalfauzan.mykotlinapp.R
import net.iqbalfauzan.mykotlinapp.submission_akhir.Fragment.Fragment_Next
import net.iqbalfauzan.mykotlinapp.submission_akhir.Fragment.Fragment_Overview
import net.iqbalfauzan.mykotlinapp.submission_akhir.Fragment.Fragment_Players
import net.iqbalfauzan.mykotlinapp.submission_akhir.Fragment.Fragment_Prev
import net.iqbalfauzan.mykotlinapp.submission_akhir.Model.ModelTeam
import net.iqbalfauzan.mykotlinapp.submission_akhir.Presenter.TeamPresenter
import net.iqbalfauzan.mykotlinapp.submission_akhir.View.TeamView
import net.iqbalfauzan.mykotlinapp.submission_dua.database.Favorite
import net.iqbalfauzan.mykotlinapp.submission_dua.database.database
import net.iqbalfauzan.mykotlinapp.submission_dua.details.DetailsPresenter
import net.iqbalfauzan.mykotlinapp.utils.invisible
import net.iqbalfauzan.mykotlinapp.utils.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.themedTabLayout
import org.jetbrains.anko.support.v4.viewPager

class TeamActivity : AppCompatActivity(), AnkoLogger, TeamView{
    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamList(data: List<ModelTeam>) {
        Picasso.get().load(data.get(0).teamBadge).into(imageLogo)
        textNamaTim.text = data.get(0).teamName
        textStadionTi.text = data.get(0).teamStadium
        textTahunTim.text = data.get(0).teamTahun
    }

    private lateinit var mTabLayout: TabLayout
    private lateinit var myViewPager: ViewPager
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var progressBar: ProgressBar
    private lateinit var textNamaTim: TextView
    private lateinit var textTahunTim: TextView
    private lateinit var textStadionTi: TextView
    private lateinit var imageLogo: ImageView
    private lateinit var presenter: TeamPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val idTeam = intent.getStringExtra("idTeam")
        coordinatorLayout {
            lparams(matchParent, matchParent)

            appBarLayout {
                lparams(matchParent, wrapContent)
                linearLayout {
                    lparams(matchParent, wrapContent)
                    orientation = LinearLayout.VERTICAL
                    progressBar = progressBar {
                    }.lparams{

                    }
                    imageLogo = imageView {
                        id = R.id.imageTim
                    }.lparams(matchParent, 150)
                    textNamaTim = textView {
                        id = R.id.textNamaTim
                        gravity = Gravity.CENTER
                        textSize = 16.0F
                        textColorResource = R.color.putih
                    }
                    textTahunTim = textView {
                        id = R.id.textTahunTim
                        gravity = Gravity.CENTER
                        textColorResource = R.color.putih
                    }
                    textStadionTi = textView {
                        id = R.id.textStadionTim
                        gravity = Gravity.CENTER
                        textColorResource = R.color.putih
                    }
                }
                mTabLayout = themedTabLayout(R.style.ThemeOverlay_AppCompat_Dark) {
                    lparams(matchParent, wrapContent)
                    {
                        tabMode = TabLayout.MODE_FIXED
                    }
                }
            }
            myViewPager = viewPager {
                id = R.id.viewpager
            }.lparams(matchParent, matchParent)
            (myViewPager!!.layoutParams as CoordinatorLayout.LayoutParams).behavior = AppBarLayout.ScrollingViewBehavior()
        }
        val bundle = Bundle()
        setupViewPager(myViewPager, bundle, idTeam)
        mTabLayout.setupWithViewPager(myViewPager)
        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamPresenter(this, request, gson)
        presenter.getTeam(idTeam)
    }
    fun setupViewPager(viewPager: ViewPager, bundle: Bundle, idTeam:String) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        val args = bundle
        args.putString("idTeam", idTeam)
        val fragmentOverview = Fragment_Overview()
        fragmentOverview.arguments = args
        adapter.addFragment(fragmentOverview, "OVERVIEWS")
        val fragmentPlayer = Fragment_Players()
        fragmentPlayer.arguments = args
        adapter.addFragment(fragmentPlayer, "PLAYERS")
        viewPager.adapter = adapter

    }
    class ViewPagerAdapter(fragmentManager: FragmentManager?) : FragmentPagerAdapter(fragmentManager){
        var fragment: ArrayList<Fragment> = ArrayList()
        var fragmentTitle: ArrayList<String> = ArrayList()

        override fun getItem(position: Int): Fragment {
            return fragment[position]
        }

        override fun getCount(): Int {
            return fragment.size
        }

        fun addFragment(fragmentt: Fragment, title: String) {
            fragment.add(fragmentt)
            fragmentTitle.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return fragmentTitle[position]
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menui, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home->{
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else-> super.onOptionsItemSelected(item)
        }
    }
    private fun addToFavorite(){
        try {

            toast("Added to favorite").show()
        } catch (e: SQLiteConstraintException){
            toast(e.localizedMessage).show()
        }
    }
    private fun favoriteState(){

    }

    private fun removeFromFavorite(){
        try {

            toast("Removed to favorite").show()
        } catch (e: SQLiteConstraintException){
            toast(e.localizedMessage).show()
        }
    }
    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }
}
