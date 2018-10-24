package net.iqbalfauzan.mykotlinapp.submission_dua.prev

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_prev_match.view.*
import net.iqbalfauzan.mykotlinapp.R
import net.iqbalfauzan.mykotlinapp.submission_akhir.Model.ModelMatch

class PrevMatchAdapter(private val context: Context, private val prevMatch: List<ModelMatch>,
                       private val listener: (ModelMatch)-> Unit)
    : RecyclerView.Adapter<PrevMatchAdapter.PrevMatchHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            PrevMatchHolder(LayoutInflater.from(context).inflate(R.layout.item_prev_match, parent, false))

    override fun getItemCount(): Int = prevMatch.size

    override fun onBindViewHolder(holder: PrevMatchAdapter.PrevMatchHolder, position: Int) {
        holder.bindItem(prevMatch[position], listener)
    }

    class PrevMatchHolder(override val containerView:View) : RecyclerView.ViewHolder(containerView), LayoutContainer{
        fun bindItem(items: ModelMatch, listener: (ModelMatch) -> Unit){
            itemView.textDate.text = items.tanggal
            itemView.textHome.text = items.namaHome
            itemView.textAway.text = items.namaAway
            val home: Int? = items.scoreHome
            val away: Int? = items.scoreAway
            if (home == null || away == null){
                itemView.textScore.text = " VS "
            }else{
                itemView.textScore.text = home.toString()+" VS "+away.toString()
            }
            containerView.setOnClickListener { listener(items) }
        }
    }


}