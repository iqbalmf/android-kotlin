package net.iqbalfauzan.mykotlinapp.submission_dua

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*
import net.iqbalfauzan.mykotlinapp.R
import net.iqbalfauzan.mykotlinapp.submission_dua.fav.FavMatchFragment
import net.iqbalfauzan.mykotlinapp.submission_dua.next.NextMatchFragment
import net.iqbalfauzan.mykotlinapp.submission_dua.prev.PrevMatchFragment

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.prev_match -> {
                    loadPrevMatch(savedInstanceState)
                }
                R.id.next_match -> {
                    loadNextMatch(savedInstanceState)
                }
                R.id.favorite_match ->{
                    loadFavoriteMatch(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = R.id.prev_match
    }
    private fun loadPrevMatch(savedInstanceState: Bundle?){
        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(R.id.main_container, PrevMatchFragment(), PrevMatchFragment::class.java.simpleName)
                    .commit()
        }
    }
    private fun loadNextMatch(savedInstanceState: Bundle?){
        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(R.id.main_container, NextMatchFragment(), PrevMatchFragment::class.java.simpleName)
                    .commit()
        }
    }
    private fun loadFavoriteMatch(savedInstanceState: Bundle?){
        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(R.id.main_container, (FavMatchFragment()), PrevMatchFragment::class.java.simpleName)
                    .commit()
        }
    }
}
