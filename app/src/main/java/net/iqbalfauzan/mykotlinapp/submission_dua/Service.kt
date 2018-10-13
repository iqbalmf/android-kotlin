package net.iqbalfauzan.mykotlinapp.submission_dua

import net.iqbalfauzan.mykotlinapp.submission_dua.prev.ModelPrevMatch
import okhttp3.ResponseBody
import retrofit.Call
import retrofit.Response
import retrofit.http.GET
import retrofit.http.Query
import rx.Observable
import java.util.*

interface Service {
    @GET("api/v1/json/1/lookupevent.php")
    fun getEvent(@Query("id") id: String): Observable<ModelPrevMatch>
}