package com.muazzeznihalbahadir.invio_case.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Karakter(
     var id : Int,
     var name: String,
     var status : String,
     var species: String,
     var gender: String,
     var origin : LokasyonData,
     var location : LokasyonData,
     var image : String,
     var episode : List<String>,
     var created : String
):Parcelable
