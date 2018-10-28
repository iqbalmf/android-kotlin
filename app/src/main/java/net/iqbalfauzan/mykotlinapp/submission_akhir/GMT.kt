package net.iqbalfauzan.mykotlinapp.submission_akhir

import java.text.SimpleDateFormat
import java.util.*

class GMT{
    fun toGMTFormat(date: String?, time: String?): Date? {
        val locale = Locale("in", "ID")
        val formatter = SimpleDateFormat("dd/MM/yy HH:mm:ss", locale)
        formatter.timeZone = TimeZone.getTimeZone("UTC")
        val dateTime = "$date $time"
        return formatter.parse(dateTime)
    }
}