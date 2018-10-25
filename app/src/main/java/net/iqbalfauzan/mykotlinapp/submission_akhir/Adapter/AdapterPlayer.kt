package net.iqbalfauzan.mykotlinapp.submission_akhir.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_player_list.view.*
import kotlinx.android.synthetic.main.item_prev_match.view.*
import net.iqbalfauzan.mykotlinapp.R
import net.iqbalfauzan.mykotlinapp.submission_akhir.GMT
import net.iqbalfauzan.mykotlinapp.submission_akhir.Model.ModelMatch
import net.iqbalfauzan.mykotlinapp.submission_akhir.Model.ModelTeam
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk15.coroutines.onClick

data class AdapterPlayer(private val  teams:List<ModelTeam>,
                         private val listener:(ModelTeam) -> Unit)
    :RecyclerView.Adapter<AdapterPlayer.HolderPlayer>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            AdapterPlayer.HolderPlayer(LayoutInflater.from(parent.context).inflate(R.layout.item_player_list, parent, false))
    class HolderPlayer (override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(items: ModelTeam, listener: (ModelTeam) -> Unit){
            itemView.player_name.text = items.namaPlayer
            itemView.player_position.text = items.posisiPlayer
            Picasso.get().load(items.fotoPlayer).into(itemView.player_photo)
            containerView.setOnClickListener { listener(items) }
        }
    }
    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: HolderPlayer, position: Int) {
        holder.bindItem(teams[position], listener)
    }

}