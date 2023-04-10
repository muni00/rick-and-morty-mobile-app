package com.muazzeznihalbahadir.invio_case.api

import com.muazzeznihalbahadir.invio_case.model.Karakter
import com.muazzeznihalbahadir.invio_case.model.KarakterList
import com.muazzeznihalbahadir.invio_case.model.LokasyonList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SimpleApi {

    @GET("api/character")
    suspend fun getCharacters(@Query("page") page:Int) : Response<KarakterList>

    @GET("api/location")
    suspend fun getLocations(@Query("page") page:Int) : Response<LokasyonList>

    @GET("api/character/{ids}")
    suspend fun getMultipleCharacters(@Path("ids") ids:String): Response<List<Karakter>>
}