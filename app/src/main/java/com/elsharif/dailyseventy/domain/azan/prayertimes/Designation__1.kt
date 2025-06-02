package com.elsharif.dailyseventy.domain.azan.prayertimes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Designation__1 {
    @SerializedName("abbreviated")
    @Expose
    var abbreviated: String? = null

    @SerializedName("expanded")
    @Expose
    var expanded: String? = null
    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(Designation__1::class.java.name).append('@')
            .append(Integer.toHexString(System.identityHashCode(this))).append('[')
        sb.append("abbreviated")
        sb.append('=')
        sb.append(if (abbreviated == null) "<null>" else abbreviated)
        sb.append(',')
        sb.append("expanded")
        sb.append('=')
        sb.append(if (expanded == null) "<null>" else expanded)
        sb.append(',')
        if (sb[sb.length - 1] == ',') {
            sb.setCharAt(sb.length - 1, ']')
        } else {
            sb.append(']')
        }
        return sb.toString()
    }
}