package com.elsharif.dailyseventy.domain.azan.prayertimes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Gregorian {
    @SerializedName("date")
    @Expose
    var date: String? = null

    @SerializedName("format")
    @Expose
    var format: String? = null

    @SerializedName("day")
    @Expose
    var day: String? = null

    @SerializedName("weekday")
    @Expose
    var weekday: Weekday? = null

    @SerializedName("month")
    @Expose
    var month: Month? = null

    @SerializedName("year")
    @Expose
    var year: String? = null

    @SerializedName("designation")
    @Expose
    var designation: Designation? = null
    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(Gregorian::class.java.name).append('@')
            .append(Integer.toHexString(System.identityHashCode(this))).append('[')
        sb.append("date")
        sb.append('=')
        sb.append(if (date == null) "<null>" else date)
        sb.append(',')
        sb.append("format")
        sb.append('=')
        sb.append(if (format == null) "<null>" else format)
        sb.append(',')
        sb.append("day")
        sb.append('=')
        sb.append(if (day == null) "<null>" else day)
        sb.append(',')
        sb.append("weekday")
        sb.append('=')
        sb.append(if (weekday == null) "<null>" else weekday)
        sb.append(',')
        sb.append("month")
        sb.append('=')
        sb.append(if (month == null) "<null>" else month)
        sb.append(',')
        sb.append("year")
        sb.append('=')
        sb.append(if (year == null) "<null>" else year)
        sb.append(',')
        sb.append("designation")
        sb.append('=')
        sb.append(if (designation == null) "<null>" else designation)
        sb.append(',')
        if (sb[sb.length - 1] == ',') {
            sb.setCharAt(sb.length - 1, ']')
        } else {
            sb.append(']')
        }
        return sb.toString()
    }
}