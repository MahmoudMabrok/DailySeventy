package com.elsharif.dailyseventy.domain.azan.prayermethods

class PrayerTimingMethod(var id: Int, var name: String?, var params: Params?, var location: Location?) {

    override fun toString(): String {
        return "PrayerTimingMethod{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", params=" + params +
                ", location=" + location +
                '}'
    }
}