package com.elsharif.dailyseventy.domain.azan.prayertimes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Offset {
    @SerializedName("Imsak")
    @Expose
    var imsak: Int? = null

    @SerializedName("Fajr")
    @Expose
    var fajr: Int? = null

    @SerializedName("Sunrise")
    @Expose
    var sunrise: Int? = null

    @SerializedName("Dhuhr")
    @Expose
    var dhuhr: Int? = null

    @SerializedName("Asr")
    @Expose
    var asr: Int? = null

    @SerializedName("Maghrib")
    @Expose
    var maghrib: Int? = null

    @SerializedName("Sunset")
    @Expose
    var sunset: Int? = null

    @SerializedName("Isha")
    @Expose
    var isha: Int? = null

    @SerializedName("Midnight")
    @Expose
    var midnight: Int? = null
    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(Offset::class.java.name).append('@')
            .append(Integer.toHexString(System.identityHashCode(this))).append('[')
        sb.append("imsak")
        sb.append('=')
        sb.append(if (imsak == null) "<null>" else imsak)
        sb.append(',')
        sb.append("fajr")
        sb.append('=')
        sb.append(if (fajr == null) "<null>" else fajr)
        sb.append(',')
        sb.append("sunrise")
        sb.append('=')
        sb.append(if (sunrise == null) "<null>" else sunrise)
        sb.append(',')
        sb.append("dhuhr")
        sb.append('=')
        sb.append(if (dhuhr == null) "<null>" else dhuhr)
        sb.append(',')
        sb.append("asr")
        sb.append('=')
        sb.append(if (asr == null) "<null>" else asr)
        sb.append(',')
        sb.append("maghrib")
        sb.append('=')
        sb.append(if (maghrib == null) "<null>" else maghrib)
        sb.append(',')
        sb.append("sunset")
        sb.append('=')
        sb.append(if (sunset == null) "<null>" else sunset)
        sb.append(',')
        sb.append("isha")
        sb.append('=')
        sb.append(if (isha == null) "<null>" else isha)
        sb.append(',')
        sb.append("midnight")
        sb.append('=')
        sb.append(if (midnight == null) "<null>" else midnight)
        sb.append(',')
        if (sb[sb.length - 1] == ',') {
            sb.setCharAt(sb.length - 1, ']')
        } else {
            sb.append(']')
        }
        return sb.toString()
    }
}