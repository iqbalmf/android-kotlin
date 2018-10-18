package net.iqbalfauzan.mykotlinapp.submission_akhir.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.iqbalfauzan.mykotlinapp.R

class Fragment_Prev:Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_prev, container, false)
        return view
    }
}