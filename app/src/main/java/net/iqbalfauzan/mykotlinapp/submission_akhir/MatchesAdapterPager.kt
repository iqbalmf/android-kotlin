package net.iqbalfauzan.mykotlinapp.submission_akhir

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import net.iqbalfauzan.mykotlinapp.submission_akhir.Fragment.Fragment_Next
import net.iqbalfauzan.mykotlinapp.submission_akhir.Fragment.Fragment_Prev

class MatchesAdapterPager(fm: FragmentManager?):FragmentPagerAdapter(fm){
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                Fragment_Next()
            }
            else -> {
                return Fragment_Prev()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }
    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "NEXT"
            else -> {
                return "LAST"
            }
        }
    }


}