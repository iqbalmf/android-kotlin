package net.iqbalfauzan.mykotlinapp.submission_dua

import net.iqbalfauzan.mykotlinapp.submission_akhir.Model.ModelMatch
import retrofit.http.GET
import retrofit.http.Query
import rx.Observable

interface Service {
    @GET("api/v1/json/1/lookupevent.php")
    fun getEvent(@Query("id") id: String): Observable<ModelMatch>
}