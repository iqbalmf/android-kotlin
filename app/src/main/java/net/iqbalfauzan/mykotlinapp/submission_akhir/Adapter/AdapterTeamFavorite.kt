package net.iqbalfauzan.mykotlinapp.submission_akhir.Adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import net.iqbalfauzan.mykotlinapp.R
import net.iqbalfauzan.mykotlinapp.submission_akhir.database.TeamFavorite
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk15.coroutines.onClick

class AdapterTeamFavorite(private val teams: List<TeamFavorite>,
                          private val listener: (TeamFavorite)->Unit)
    : RecyclerView.Adapter<HolderFavTeam>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderFavTeam {
        return HolderFavTeam(TeamFavUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: HolderFavTeam, position: Int) {
        holder.bindItem(teams[position], listener)
    }

}

class HolderFavTeam(view:View) : RecyclerView.ViewHolder(view) {
    private val teamBadge: ImageView = view.find(R.id.team_badge)
    private val teamName: TextView = view.find(R.id.team_name)
    fun bindItem(teams: TeamFavorite, listener: (TeamFavorite) -> Unit){
        Picasso.get().load(teams.teamName).into(teamBadge)
        teamName.text = teams.teamImage
        Log.i("TAG", teams.teamImage)
        Log.i("TAG", teams.teamName)
        itemView.onClick { listener(teams) }
    }

}
class TeamFavUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL

                imageView {
                    id = R.id.team_badge
                }.lparams{
                    height = dip(50)
                    width = dip(50)
                }

                textView {
                    id = R.id.team_name
                    textSize = 16f
                }.lparams{
                    margin = dip(15)
                }

            }
        }
    }

}