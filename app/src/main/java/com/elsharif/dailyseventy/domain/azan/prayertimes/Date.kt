package com.elsharif.dailyseventy.domain.azan.prayertimes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Date {
    @SerializedName("readable")
    @Expose
    var readable: String? = null

    @SerializedName("timestamp")
    @Expose
    var timestamp: String? = null

    @SerializedName("gregorian")
    @Expose
    var gregorian: Gregorian? = null

    @SerializedName("hijri")
    @Expose
    var hijri: Hijri? = null
    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(Date::class.java.name).append('@')
            .append(Integer.toHexString(System.identityHashCode(this))).append('[')
        sb.append("readable")
        sb.append('=')
        sb.append(if (readable == null) "<null>" else readable)
        sb.append(',')
        sb.append("timestamp")
        sb.append('=')
        sb.append(if (timestamp == null) "<null>" else timestamp)
        sb.append(',')
        sb.append("gregorian")
        sb.append('=')
        sb.append(if (gregorian == null) "<null>" else gregorian)
        sb.append(',')
        sb.append("hijri")
        sb.append('=')
        sb.append(if (hijri == null) "<null>" else hijri)
        sb.append(',')
        if (sb[sb.length - 1] == ',') {
            sb.setCharAt(sb.length - 1, ']')
        } else {
            sb.append(']')
        }
        return sb.toString()
    }
}