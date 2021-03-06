package net.iqbalfauzan.mykotlinapp.submission_akhir.Fragment

import android.content.Context
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.iqbalfauzan.mykotlinapp.R
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.themedTabLayout
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.viewPager
import org.jetbrains.anko.wrapContent

class FragmentFavorite:Fragment(), AnkoComponent<Context> {
    companion object {
        fun newInstance(): FragmentFavorite{
            return FragmentFavorite()
        }
    }
    private lateinit var mTabLayout: TabLayout
    private lateinit var myViewPager: ViewPager
    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        coordinatorLayout {
            lparams(matchParent, matchParent)

            appBarLayout {
                lparams(matchParent, wrapContent)

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
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewPager(myViewPager)
        mTabLayout.setupWithViewPager(myViewPager)
    }
    fun setupViewPager(viewPager: ViewPager?) {
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(Fragment_MatchesFav(), "Matches")
        adapter.addFragment(Fragment_TeamFav(), "Team")
        viewPager?.adapter = adapter

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
}