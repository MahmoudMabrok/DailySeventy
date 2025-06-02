package com.elsharif.dailyseventy.domain.azan.prayertimes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PrayerTimesResponse {
    @SerializedName("code")
    @Expose
    var code: Int? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("data")
    @Expose
    var data: List<Datum>? = null
    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(PrayerTimesResponse::class.java.name).append('@')
            .append(Integer.toHexString(System.identityHashCode(this))).append('[')
        sb.append("code")
        sb.append('=')
        sb.append(if (this.code == null) "<null>" else this.code)
        sb.append(',')
        sb.append("status")
        sb.append('=')
        sb.append(if (status == null) "<null>" else status)
        sb.append(',')
        sb.append("data")
        sb.append('=')
        sb.append(if (data == null) "<null>" else data)
        sb.append(',')
        if (sb[sb.length - 1] == ',') {
            sb.setCharAt(sb.length - 1, ']')
        } else {
            sb.append(']')
        }
        return sb.toString()
    }
}