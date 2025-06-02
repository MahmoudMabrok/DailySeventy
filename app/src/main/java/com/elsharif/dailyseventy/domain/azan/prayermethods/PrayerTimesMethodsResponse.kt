package com.elsharif.dailyseventy.domain.azan.prayermethods

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PrayerTimesMethodsResponse {
    @SerializedName("code")
    @Expose
    var code: Int? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: Data? = null

    constructor()
    constructor(code: Int?, status: String?, data: Data?) {
        this.code = code
        this.status = status
        this.data = data
    }
}