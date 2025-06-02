package com.elsharif.dailyseventy.domain.azan.prayermethods

class MOONSIGHTING : IToMethod {
    var id = 0
    var name: String? = null
    var params: Params? = null
    override fun toMethod(): PrayerTimingMethod {
        return PrayerTimingMethod(id, name, params, null)
    }
}