package com.example.core.domain.mawaryth

data class Heir(
    val name: String,
    val arName: String,
    val degree: Int,
    val relationType: HeirRelationType?,
    val sharesList: WealthShare
) {
    fun getProperShare(wealthCalculator: (WealthShare) -> Double) = wealthCalculator(sharesList);
}

enum class HeirRelationType {
    FATHER, MOTHER, SON, BROTHER, SISTER, HUSBAND, WIFE, UNCLE,
}