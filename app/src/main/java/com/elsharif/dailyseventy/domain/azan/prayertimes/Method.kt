package com.elsharif.dailyseventy.domain.azan.prayertimes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Method {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("params")
    @Expose
    var params: Params? = null

    @SerializedName("location")
    @Expose
    var location: Location? = null
    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(Method::class.java.name).append('@')
            .append(Integer.toHexString(System.identityHashCode(this))).append('[')
        sb.append("id")
        sb.append('=')
        sb.append(if (id == null) "<null>" else id)
        sb.append(',')
        sb.append("name")
        sb.append('=')
        sb.append(if (name == null) "<null>" else name)
        sb.append(',')
        sb.append("params")
        sb.append('=')
        sb.append(if (params == null) "<null>" else params)
        sb.append(',')
        sb.append("location")
        sb.append('=')
        sb.append(if (location == null) "<null>" else location)
        sb.append(',')
        if (sb[sb.length - 1] == ',') {
            sb.setCharAt(sb.length - 1, ']')
        } else {
            sb.append(']')
        }
        return sb.toString()
    }
}