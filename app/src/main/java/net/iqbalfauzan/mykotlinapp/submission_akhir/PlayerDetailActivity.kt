package net.iqbalfauzan.mykotlinapp.submission_akhir

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ProgressBar
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_player_detail.*
import net.iqbalfauzan.mykotlinapp.ApiRepository
import net.iqbalfauzan.mykotlinapp.R
import net.iqbalfauzan.mykotlinapp.submission_akhir.Model.ModelDetailPlayer
import net.iqbalfauzan.mykotlinapp.submission_akhir.Presenter.PlayerDetailPresenter
import net.iqbalfauzan.mykotlinapp.submission_akhir.Presenter.TeamPresenter
import net.iqbalfauzan.mykotlinapp.submission_akhir.View.PlayerDetailView
import org.jetbrains.anko.AnkoLogger

class PlayerDetailActivity : AppCompatActivity(), AnkoLogger, PlayerDetailView {
    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showPlayerDetail(data: List<ModelDetailPlayer>) {
        Picasso.get().load(data.get(0).strFanart1).into(imagePlayer)
        textHeight.text = data.get(0).strHeight
        textWeight.text = data.get(0).strWeight
        textPosition.text = data.get(0).strPosition
        textDetailPlayer.text = data.get(0).strDescriptionEN
        supportActionBar?.title = data.get(0).strPlayer
    }

    private lateinit var presenter: PlayerDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val idPlayer = intent.getStringExtra("idPlayer")
        val request = ApiRepository()
        val gson = Gson()
        presenter = PlayerDetailPresenter(this, request, gson)
        presenter.getPlayerDetail(idPlayer)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            android.R.id.home->{
                finish()
                true
            }
            else-> super.onOptionsItemSelected(item)
        }
    }
}
