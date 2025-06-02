package com.elsharif.dailyseventy.domain.azan.prayermethods

class TEHRAN : IToMethod {
    var id = 0
    var name: String? = null
    var params: Params? = null
    var location: Location? = null
    override fun toMethod(): PrayerTimingMethod {
        return PrayerTimingMethod(id, name, params, location)
    }
}