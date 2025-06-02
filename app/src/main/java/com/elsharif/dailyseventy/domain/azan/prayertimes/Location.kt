package com.elsharif.dailyseventy.domain.azan.prayertimes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Location {
    @SerializedName("latitude")
    @Expose
    var latitude: Double? = null

    @SerializedName("longitude")
    @Expose
    var longitude: Double? = null
    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(Location::class.java.name).append('@')
            .append(Integer.toHexString(System.identityHashCode(this))).append('[')
        sb.append("latitude")
        sb.append('=')
        sb.append(if (latitude == null) "<null>" else latitude)
        sb.append(',')
        sb.append("longitude")
        sb.append('=')
        sb.append(if (longitude == null) "<null>" else longitude)
        sb.append(',')
        if (sb[sb.length - 1] == ',') {
            sb.setCharAt(sb.length - 1, ']')
        } else {
            sb.append(']')
        }
        return sb.toString()
    }
}