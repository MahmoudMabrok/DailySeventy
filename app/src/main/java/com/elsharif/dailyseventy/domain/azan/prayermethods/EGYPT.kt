package com.elsharif.dailyseventy.domain.azan.prayermethods

data class EGYPT(
    val id: Int = 0,
    val name: String? = null,
    val params: Params? = null,
    val location: Location? = null,
) : IToMethod {

    override fun toMethod(): PrayerTimingMethod {
        return PrayerTimingMethod(id, name, params, location)
    }
}