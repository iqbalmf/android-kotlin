package net.iqbalfauzan.mykotlinapp.submission_akhir.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_prev_match.view.*
import net.iqbalfauzan.mykotlinapp.R
import net.iqbalfauzan.mykotlinapp.submission_akhir.GMT
import net.iqbalfauzan.mykotlinapp.submission_akhir.Model.ModelMatch

class AdapterPrevMatch(private val context: Context,
                       private val model:List<ModelMatch>,
                       private val listener:(ModelMatch) -> Unit) :
        RecyclerView.Adapter<AdapterPrevMatch.Holder>(){
    class Holder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(items: ModelMatch, listener: (ModelMatch) -> Unit){
            itemView.textDate.text = items.tanggal
            itemView.textHome.text = items.namaHome
            itemView.textAway.text = items.namaAway
            //var jam = items.jam?.split("+")
            //var gmt = GMT().toGMTFormat(items.strDate,jam?.get(0))
            itemView.textJam.text = items.jam
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        AdapterPrevMatch.Holder(LayoutInflater.from(context).inflate(R.layout.layout_prevmatch, parent, false))

    override fun getItemCount(): Int = model.size

    override fun onBindViewHolder(holder: AdapterPrevMatch.Holder, position: Int) {
        holder.bindItem(model[position], listener)
    }

}