package net.iqbalfauzan.mykotlinapp.submission_akhir

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main3.*
import net.iqbalfauzan.mykotlinapp.R
import net.iqbalfauzan.mykotlinapp.submission_akhir.Fragment.Fragment_Favorite
import net.iqbalfauzan.mykotlinapp.submission_akhir.Fragment.Fragment_Matches
import net.iqbalfauzan.mykotlinapp.submission_akhir.Fragment.Fragment_Teams

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.matches -> {
                    loadFragmentMatches(savedInstanceState)
                }
                R.id.teams-> {
                    loadFragmentTeams(savedInstanceState)
                }
                R.id.favorite ->{
                    loadFragmentFavorite(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = R.id.matches
    }
    private fun loadFragmentMatches(savedInstanceState: Bundle?){
        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(R.id.main_container, Fragment_Matches(), Fragment_Matches::class.java.simpleName).commit()
        }
    }
    private fun loadFragmentTeams(savedInstanceState: Bundle?){
        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(R.id.main_container, Fragment_Teams(), Fragment_Teams::class.java.simpleName).commit()
        }
    }
    private fun loadFragmentFavorite(savedInstanceState: Bundle?){
        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(R.id.main_container, Fragment_Favorite(), Fragment_Favorite::class.java.simpleName).commit()
        }
    }
}
