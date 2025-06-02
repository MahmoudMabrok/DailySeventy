package com.elsharif.dailyseventy.domain.azan.prayertimes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Meta {
    @SerializedName("latitude")
    @Expose
    var latitude: Double? = null

    @SerializedName("longitude")
    @Expose
    var longitude: Double? = null

    @SerializedName("timezone")
    @Expose
    var timezone: String? = null

    @SerializedName("method")
    @Expose
    var method: Method? = null

    @SerializedName("latitudeAdjustmentMethod")
    @Expose
    var latitudeAdjustmentMethod: String? = null

    @SerializedName("midnightMode")
    @Expose
    var midnightMode: String? = null

    @SerializedName("school")
    @Expose
    var school: String? = null

    @SerializedName("offset")
    @Expose
    var offset: Offset? = null
    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(Meta::class.java.name).append('@')
            .append(Integer.toHexString(System.identityHashCode(this))).append('[')
        sb.append("latitude")
        sb.append('=')
        sb.append(if (latitude == null) "<null>" else latitude)
        sb.append(',')
        sb.append("longitude")
        sb.append('=')
        sb.append(if (longitude == null) "<null>" else longitude)
        sb.append(',')
        sb.append("timezone")
        sb.append('=')
        sb.append(if (timezone == null) "<null>" else timezone)
        sb.append(',')
        sb.append("method")
        sb.append('=')
        sb.append(if (method == null) "<null>" else method)
        sb.append(',')
        sb.append("latitudeAdjustmentMethod")
        sb.append('=')
        sb.append(if (latitudeAdjustmentMethod == null) "<null>" else latitudeAdjustmentMethod)
        sb.append(',')
        sb.append("midnightMode")
        sb.append('=')
        sb.append(if (midnightMode == null) "<null>" else midnightMode)
        sb.append(',')
        sb.append("school")
        sb.append('=')
        sb.append(if (school == null) "<null>" else school)
        sb.append(',')
        sb.append("offset")
        sb.append('=')
        sb.append(if (offset == null) "<null>" else offset)
        sb.append(',')
        if (sb[sb.length - 1] == ',') {
            sb.setCharAt(sb.length - 1, ']')
        } else {
            sb.append(']')
        }
        return sb.toString()
    }
}