package net.iqbalfauzan.mykotlinapp.submission_akhir.Adapter

import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_prev_match.view.*
import net.iqbalfauzan.mykotlinapp.R
import net.iqbalfauzan.mykotlinapp.submission_akhir.GMT
import net.iqbalfauzan.mykotlinapp.submission_akhir.Model.ModelMatch

class AdapterNextMatch(private val context: Context,
                       private val model:List<ModelMatch>,
                       private val listener:(ModelMatch) -> Unit)
    : RecyclerView.Adapter<AdapterNextMatch.Holder>() {
    class Holder (override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer{
        fun bindItem(items: ModelMatch, listener: (ModelMatch) -> Unit){
            itemView.textDate.text = items.tanggal
            itemView.textHome.text = items.namaHome
            itemView.textAway.text = items.namaAway
            if (items.strDate != null){
                val jam = items.jam?.split("+")
                val gmt = GMT().toGMTFormat(items.strDate,jam?.get(0))
                val jam1 = gmt.toString().split(" ")
                val jam2 = jam1?.get(3)
                itemView.textJam.text = jam2
            }else{
                itemView.textJam.text = items.jam
            }
            itemView.textScore.text = " VS "
            containerView.setOnClickListener { listener(items) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            AdapterNextMatch.Holder(LayoutInflater.from(context).inflate(R.layout.item_prev_match, parent, false))
    override fun getItemCount(): Int = model.size

    override fun onBindViewHolder(holder: AdapterNextMatch.Holder, position: Int) {
        holder.bindItem(model[position], listener)
        holder.itemView.imageNotif.setOnClickListener{
            val intent = Intent(Intent.ACTION_EDIT)
            intent.type = "vnd.android.cursor.item/event"
            intent.putExtra(CalendarContract.Events.TITLE, model[position].namaHome+" VS "+model[position].namaAway)
            context.startActivity(intent)
        }
    }


}
