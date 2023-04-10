package com.muazzeznihalbahadir.invio_case.api

import com.muazzeznihalbahadir.invio_case.model.Karakter
import com.muazzeznihalbahadir.invio_case.model.KarakterList
import com.muazzeznihalbahadir.invio_case.model.LokasyonList
import retrofit2.Response

class Repository {

    suspend fun getCharacters(page: Int) : Response<KarakterList>{
        return RetrofitInstance.api.getCharacters(page)
    }

    suspend fun getLocations(page: Int) : Response<LokasyonList>{
        return RetrofitInstance.api.getLocations(page)
    }

    suspend fun getMultipleCharacters(ids:String) : Response<List<Karakter>>{
        var degisken = RetrofitInstance.api.getMultipleCharacters(ids)
        return degisken
    }
}