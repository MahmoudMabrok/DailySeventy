package com.elsharif.dailyseventy.domain.azan.prayertimes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Hijri {
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
    var weekday: Weekday__1? = null

    @SerializedName("month")
    @Expose
    var month: Month__1? = null

    @SerializedName("year")
    @Expose
    var year: String? = null

    @SerializedName("designation")
    @Expose
    var designation: Designation__1? = null

    @SerializedName("holidays")
    @Expose
    var holidays: List<Any>? = null
    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(Hijri::class.java.name).append('@')
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
        sb.append("holidays")
        sb.append('=')
        sb.append(if (holidays == null) "<null>" else holidays)
        sb.append(',')
        if (sb[sb.length - 1] == ',') {
            sb.setCharAt(sb.length - 1, ']')
        } else {
            sb.append(']')
        }
        return sb.toString()
    }
}