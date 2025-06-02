package com.elsharif.dailyseventy.domain.azan.prayermethods

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Data {
    @SerializedName("MWL")
    @Expose
    var mWL: MWL? = null

    @SerializedName("ISNA")
    @Expose
    var iSNA: ISNA? = null

    @SerializedName("EGYPT")
    @Expose
    var eGYPT: EGYPT? = null

    @SerializedName("MAKKAH")
    @Expose
    var mAKKAH: MAKKAH? = null

    @SerializedName("KARACHI")
    @Expose
    var kARACHI: KARACHI? = null

    @SerializedName("TEHRAN")
    @Expose
    var tEHRAN: TEHRAN? = null

    @SerializedName("JAFARI")
    @Expose
    var jAFARI: JAFARI? = null

    @SerializedName("GULF")
    @Expose
    var gULF: GULF? = null

    @SerializedName("KUWAIT")
    @Expose
    var kUWAIT: KUWAIT? = null

    @SerializedName("QATAR")
    @Expose
    var qATAR: QATAR? = null

    @SerializedName("SINGAPORE")
    @Expose
    var sINGAPORE: SINGAPORE? = null

    @SerializedName("FRANCE")
    @Expose
    var fRANCE: FRANCE? = null

    @SerializedName("TURKEY")
    @Expose
    var tURKEY: TURKEY? = null

    @SerializedName("RUSSIA")
    @Expose
    var rUSSIA: RUSSIA? = null

    @SerializedName("MOONSIGHTING")
    @Expose
    var mOONSIGHTING: MOONSIGHTING? = null
    fun getmWL(): MWL? {
        return mWL
    }

    fun getiSNA(): ISNA? {
        return iSNA
    }

    fun geteGYPT(): EGYPT? {
        return eGYPT
    }

    fun getmAKKAH(): MAKKAH? {
        return mAKKAH
    }

    fun getkARACHI(): KARACHI? {
        return kARACHI
    }

    fun gettEHRAN(): TEHRAN? {
        return tEHRAN
    }

    fun getjAFARI(): JAFARI? {
        return jAFARI
    }

    fun getgULF(): GULF? {
        return gULF
    }

    fun getkUWAIT(): KUWAIT? {
        return kUWAIT
    }

    fun getqATAR(): QATAR? {
        return qATAR
    }

    fun getsINGAPORE(): SINGAPORE? {
        return sINGAPORE
    }

    fun getfRANCE(): FRANCE? {
        return fRANCE
    }

    fun gettURKEY(): TURKEY? {
        return tURKEY
    }

    fun getrUSSIA(): RUSSIA? {
        return rUSSIA
    }

    fun getmOONSIGHTING(): MOONSIGHTING? {
        return mOONSIGHTING
    }
}