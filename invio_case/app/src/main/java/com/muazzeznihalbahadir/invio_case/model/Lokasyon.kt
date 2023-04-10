package com.muazzeznihalbahadir.invio_case.model

data class Lokasyon (
    var id : Int,
    var name: String,
    var type : String,
    var dimension: String,
    var residents: List<String>,
    var url : String,
    var created : String
    )