package com.elsharif.dailyseventy.domain.azan.prayertimes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Datum(
    @SerializedName("timings")
    @Expose
    val timings: Timings,

    @SerializedName("date")
    @Expose
    val date: Date,

    @SerializedName("meta")
    @Expose
    val meta: Meta
) {

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(Datum::class.java.name).append('@')
            .append(Integer.toHexString(System.identityHashCode(this))).append('[')
        sb.append("timings")
        sb.append('=')
        sb.append(timings)
        sb.append(',')
        sb.append("date")
        sb.append('=')
        sb.append(date)
        sb.append(',')
        sb.append("meta")
        sb.append('=')
        sb.append(meta)
        sb.append(',')
        if (sb[sb.length - 1] == ',') {
            sb.setCharAt(sb.length - 1, ']')
        } else {
            sb.append(']')
        }
        return sb.toString()
    }
}