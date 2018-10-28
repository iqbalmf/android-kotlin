package net.iqbalfauzan.mykotlinapp.submission_dua.fav

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_prev_match.view.*
import net.iqbalfauzan.mykotlinapp.R
import net.iqbalfauzan.mykotlinapp.submission_dua.database.Favorite

class FavoriteAdapter(private val context: Context, private val prevMatch: List<Favorite>,
                      private val listener: (Favorite)-> Unit) :
        RecyclerView.Adapter<FavoriteAdapter.FavHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            FavHolder(LayoutInflater.from(context).inflate(R.layout.layout_prevmatch, parent, false))

    override fun getItemCount(): Int = prevMatch.size

    override fun onBindViewHolder(holder: FavoriteAdapter.FavHolder, position: Int) {
        holder.bindItem(prevMatch[position], listener)
    }

    class FavHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(items: Favorite, listener: (Favorite) -> Unit){
            itemView.textDate.text = items.dateMatch
            itemView.textHome.text = items.homeName
            itemView.textAway.text = items.awayName
            val home: String? = items.homeScore
            val away: String? = items.awayScore
            if (home == null || away == null || home.equals("null") || away.equals("null")){
                itemView.textScore.text = " VS "
            }else{
                itemView.textScore.text = home.toString()+" VS "+away.toString()
            }
            containerView.setOnClickListener { listener(items) }
        }
    }



}