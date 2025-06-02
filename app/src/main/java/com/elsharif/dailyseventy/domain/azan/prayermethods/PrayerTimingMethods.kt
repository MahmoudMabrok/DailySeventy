package com.elsharif.dailyseventy.domain.azan.prayermethods

class PrayerTimingMethods(data: Data) {
    val methods: ArrayList<PrayerTimingMethod?> = ArrayList()

    init {
        methods.add(data.geteGYPT()!!.toMethod())
        methods.add(data.getfRANCE()!!.toMethod())
        methods.add(data.getgULF()!!.toMethod())
        methods.add(data.getiSNA()!!.toMethod())
        methods.add(data.getjAFARI()!!.toMethod())
        methods.add(data.getkARACHI()!!.toMethod())
        methods.add(data.getkUWAIT()!!.toMethod())
        methods.add(data.getmAKKAH()!!.toMethod())
        methods.add(data.getmOONSIGHTING()!!.toMethod())
        methods.add(data.getmWL()!!.toMethod())
        methods.add(data.getqATAR()!!.toMethod())
        methods.add(data.getrUSSIA()!!.toMethod())
        methods.add(data.getsINGAPORE()!!.toMethod())
        methods.add(data.gettEHRAN()!!.toMethod())
        methods.add(data.gettURKEY()!!.toMethod())
        methods.sortedBy { prayerTimingMethod -> prayerTimingMethod?.id }
    }

    override fun toString(): String {
        return "PrayerTimingMethods{" +
                "methods=" + methods +
                '}'
    }
}