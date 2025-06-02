package com.elsharif.dailyseventy.domain.azan.prayertimes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Month__1 {
    @SerializedName("number")
    @Expose
    var number: Int? = null

    @SerializedName("en")
    @Expose
    var en: String? = null

    @SerializedName("ar")
    @Expose
    var ar: String? = null
    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(Month__1::class.java.name).append('@')
            .append(Integer.toHexString(System.identityHashCode(this))).append('[')
        sb.append("number")
        sb.append('=')
        sb.append(if (number == null) "<null>" else number)
        sb.append(',')
        sb.append("en")
        sb.append('=')
        sb.append(if (en == null) "<null>" else en)
        sb.append(',')
        sb.append("ar")
        sb.append('=')
        sb.append(if (ar == null) "<null>" else ar)
        sb.append(',')
        if (sb[sb.length - 1] == ',') {
            sb.setCharAt(sb.length - 1, ']')
        } else {
            sb.append(']')
        }
        return sb.toString()
    }
}