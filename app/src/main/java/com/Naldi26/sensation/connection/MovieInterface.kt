package com.Naldi26.sensation.connection

import com.Naldi26.sensation.model.ResponseMovieModel
import retrofit2.http.GET
import retrofit2.http.Query
import io.reactivex.Observable
import java.util.*

interface MovieInterface {

    @GET("movie/now_playing")
    fun getListMovieNowPlaying(
        @Query("api_key")
        apiKey: String
    ): Observable<ResponseMovieModel>
}