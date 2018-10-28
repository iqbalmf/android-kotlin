package net.iqbalfauzan.mykotlinapp.submission_dua.details

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import net.iqbalfauzan.mykotlinapp.R
import net.iqbalfauzan.mykotlinapp.R.drawable.ic_add_to_favorites
import net.iqbalfauzan.mykotlinapp.R.drawable.ic_added_to_favorites
import net.iqbalfauzan.mykotlinapp.submission_akhir.Model.ModelMatch
import net.iqbalfauzan.mykotlinapp.ApiRepository
import net.iqbalfauzan.mykotlinapp.submission_akhir.database.Favorite
import net.iqbalfauzan.mykotlinapp.submission_akhir.database.database
import net.iqbalfauzan.mykotlinapp.submission_dua.fav.ModelFavorite
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.info
import org.jetbrains.anko.toast

class DetailActivity : AppCompatActivity(), AnkoLogger, DetailsView{

    private lateinit var presenter: DetailsPresenter
    private var menuItem: Menu? = null
    private lateinit var teams: ModelFavorite
    private var isFavorite: Boolean = false
    private lateinit var idMatch: String
    var scoreHome: String = ""
    var scoreAway: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val idHome= intent.getStringExtra("idHome")
        val idAway = intent.getStringExtra("idAway")
        idMatch = intent.getStringExtra("idMatch")
        favoriteState()
        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailsPresenter(this, gson, request)
        presenter.getEventDetails(idMatch)
        presenter.getBadgeDetailsAway(idAway)
        presenter.getBadgeDetailsHome(idHome)

    }
    override fun showTeamHome(data: List<ModelTeamHome>) {
        info { data.get(0).teamBadge }
        Picasso.get().load(data.get(0).teamBadge).into(imageHome)
    }

    override fun showTeamAway(data: List<ModelTeamAway>) {
        info { data.get(0).teamBadge }
        Picasso.get().load(data.get(0).teamBadge).into(imageAway)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMatchList(data: List<ModelMatch>) {
        teams = ModelFavorite(data[0].idMatch,
                data[0].scoreHome.toString(),
                data[0].scoreAway.toString(),
                data[0].namaHome,
                data[0].namaAway,
                data[0].idHome,
                data[0].idAway,
                data[0].tanggal,
                data[0].jam)
        textTanggal.text = data.get(0).tanggal
        textJam.text = data.get(0).jam
        textFormasiHome.text = data.get(0).HomeFormation
        textFormasiAway.text = data.get(0).AwayFormation
        textHomeName.text = data.get(0).namaHome
        textAwayName.text = data.get(0).namaAway
        scoreHome = data.get(0).scoreHome.toString()
        scoreAway = data.get(0).scoreAway.toString()

        if (scoreHome.equals("null") || scoreAway.equals("null")){
            scoreHome = ""
            scoreAway = ""
            textScoreHome.text = scoreHome
            textScoreAway.text = scoreAway
        }else{
            textScoreHome.text = data.get(0).scoreHome.toString()
            textScoreAway.text = data.get(0).scoreAway.toString()
        }
        goalsHome.text = data.get(0).homeGoalDetails
        goalsAway.text = data.get(0).AwayGoalDetails
        shotsHome.text = data.get(0).homeShots.toString()
        shotsAway.text = data.get(0).awayShots.toString()
        KeeperHome.text = data.get(0).HomeLineupGoalkeeper
        KeeperAway.text = data.get(0).AwayLineupGoalkeeper
        defenseHome.text = data.get(0).HomeLineupDefense
        defenseHome.text = data.get(0).AwayLineupDefense
        midHome.text = data.get(0).HomeLineupMidfield
        midAway.text = data.get(0).AwayLineupMidfield
        forwardHome.text = data.get(0).HomeLineupForward
        forwardAway.text = data.get(0).AwayLineupForward
        subHome.text = data.get(0).HomeLineupSubstitutes
        subAway.text = data.get(0).AwayLineupSubstitutes

    }





    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menui, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun addToFavorite(){
        try {
            database.use {
                insert(Favorite.TABLE_FAVORITE,Favorite.MatchID to teams.idMatch,
                        Favorite.HOME_NAME to teams.namaHome,
                        Favorite.ID_HOME to teams.idHome,
                        Favorite.AWAY_NAME to teams.namaAway,
                        Favorite.ID_AWAY to teams.idAway,
                        Favorite.DATE_MATCH to teams.tanggal,
                        Favorite.TIME_MATCH to teams.jam,
                        Favorite.HOME_SCORE to teams.scoreHome,
                        Favorite.AWAY_SCORE to teams.scoreAway)
            }
            toast("Added to favorite").show()
        } catch (e: SQLiteConstraintException){
            toast(e.localizedMessage).show()
        }
    }
    private fun favoriteState(){
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                    .whereArgs("(MatchID = {id})",
                            "id" to idMatch)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(MatchID = {id})",
                        "id" to idMatch)
            }
            toast("Removed to favorite").show()
        } catch (e: SQLiteConstraintException){
            toast(e.localizedMessage).show()
        }
    }
    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }
}
