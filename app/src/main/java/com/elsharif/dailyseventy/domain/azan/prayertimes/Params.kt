package com.elsharif.dailyseventy.domain.azan.prayertimes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Params {
    @SerializedName("Fajr")
    @Expose
    var fajr: Double? = null

    @SerializedName("Isha")
    @Expose
    var isha: Double? = null
    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(Params::class.java.name).append('@')
            .append(Integer.toHexString(System.identityHashCode(this))).append('[')
        sb.append("fajr")
        sb.append('=')
        sb.append(if (fajr == null) "<null>" else fajr)
        sb.append(',')
        sb.append("isha")
        sb.append('=')
        sb.append(if (isha == null) "<null>" else isha)
        sb.append(',')
        if (sb[sb.length - 1] == ',') {
            sb.setCharAt(sb.length - 1, ']')
        } else {
            sb.append(']')
        }
        return sb.toString()
    }
}