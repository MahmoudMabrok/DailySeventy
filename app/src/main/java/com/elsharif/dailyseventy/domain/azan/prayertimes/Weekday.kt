package com.elsharif.dailyseventy.domain.azan.prayertimes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Weekday {
    @SerializedName("en")
    @Expose
    var en: String? = null
    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(Weekday::class.java.name).append('@')
            .append(Integer.toHexString(System.identityHashCode(this))).append('[')
        sb.append("en")
        sb.append('=')
        sb.append(if (en == null) "<null>" else en)
        sb.append(',')
        if (sb[sb.length - 1] == ',') {
            sb.setCharAt(sb.length - 1, ']')
        } else {
            sb.append(']')
        }
        return sb.toString()
    }
}