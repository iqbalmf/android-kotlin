package net.iqbalfauzan.mykotlinapp.submission_akhir.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.ScrollingTabContainerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_prev_match.view.*
import net.iqbalfauzan.mykotlinapp.R
import net.iqbalfauzan.mykotlinapp.submission_akhir.GMT
import net.iqbalfauzan.mykotlinapp.submission_akhir.Model.ModelMatch
import net.iqbalfauzan.mykotlinapp.submission_dua.next.NextMatchAdapter
import org.jetbrains.anko.sdk15.coroutines.onClick
import java.text.SimpleDateFormat
import java.util.*

class AdapterNextMatch(private val context: Context,
                       private val model:List<ModelMatch>,
                       private val listener:(ModelMatch) -> Unit)
    : RecyclerView.Adapter<AdapterNextMatch.Holder>() {
    class Holder (override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer{
        fun bindItem(items: ModelMatch, listener: (ModelMatch) -> Unit){
            itemView.textDate.text = items.tanggal
            itemView.textHome.text = items.namaHome
            itemView.textAway.text = items.namaAway
            var jam = items.jam?.split("+")
            var gmt = GMT().toGMTFormat(items.strDate,jam?.get(0))
            itemView.textJam.text = gmt.toString()

            itemView.textScore.text = " VS "
            itemView.imageNotif.onClick {

            }
            containerView.setOnClickListener { listener(items) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            AdapterNextMatch.Holder(LayoutInflater.from(context).inflate(R.layout.item_prev_match, parent, false))
    override fun getItemCount(): Int = model.size

    override fun onBindViewHolder(holder: AdapterNextMatch.Holder, position: Int) {
        holder.bindItem(model[position], listener)
    }


}
