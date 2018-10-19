package net.iqbalfauzan.mykotlinapp.submission_akhir

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import net.iqbalfauzan.mykotlinapp.R
import net.iqbalfauzan.mykotlinapp.submission_akhir.Fragment.Fragment_Next
import net.iqbalfauzan.mykotlinapp.submission_akhir.Fragment.Fragment_Overview
import net.iqbalfauzan.mykotlinapp.submission_akhir.Fragment.Fragment_Players
import net.iqbalfauzan.mykotlinapp.submission_akhir.Fragment.Fragment_Prev
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.themedTabLayout
import org.jetbrains.anko.support.v4.viewPager

class TeamActivity : AppCompatActivity(){
    private lateinit var mTabLayout: TabLayout
    private lateinit var myViewPager: ViewPager
    private lateinit var textNamaTim: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        coordinatorLayout {
            lparams(matchParent, matchParent)

            appBarLayout {
                lparams(matchParent, wrapContent)
                linearLayout {
                    lparams(matchParent, wrapContent)
                    orientation = LinearLayout.VERTICAL
                    imageView {
                        id = R.id.imageTim
                    }
                    textView {
                        id = R.id.textNamaTim
                        gravity = Gravity.CENTER
                    }
                    textView {
                        id = R.id.textTahunTim
                        gravity = Gravity.CENTER
                    }
                    textView {
                        id = R.id.textStadionTim
                        gravity = Gravity.CENTER
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
        setupViewPager(myViewPager)
        mTabLayout.setupWithViewPager(myViewPager)
    }
    fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(Fragment_Overview(), "OVERVIEWS")
        adapter.addFragment(Fragment_Players(), "PLAYERS")
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
}
