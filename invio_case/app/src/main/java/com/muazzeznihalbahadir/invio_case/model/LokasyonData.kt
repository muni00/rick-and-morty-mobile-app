package com.muazzeznihalbahadir.invio_case.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LokasyonData(
    var name : String,
    var url : String
):Parcelable
