package com.example.core.domain.mawaryth

data class WealthShare(
    val upperShare: Double,
    val lowerShare: Double,
    val theRestShare: WealthShareRestCalculator?,
    val none: Double = 0.0
)

fun interface WealthShareRestCalculator {
    fun invoke(heirs: List<Heir>): Double
}