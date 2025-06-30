package com.example.core.domain.mawaryth

object WealthShares {

    fun husbandWealthShare() = WealthShare(0.5, .25, null)
    fun wifeWealthShare() = WealthShare(.25, .125, null)
    fun fatherWealthShare(calculator: WealthShareRestCalculator) =
        WealthShare(1.0 / 6.0, 1.0 / 6.0, calculator)
    fun motherWealthShare(calculator: WealthShareRestCalculator) =
        WealthShare(1.0 / 3.0, 1.0 / 6.0, calculator)
}