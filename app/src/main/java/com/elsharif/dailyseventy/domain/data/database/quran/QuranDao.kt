package com.elsharif.dailyseventy.domain.data.database.quran

import androidx.room.Dao
import androidx.room.Query
import com.elsharif.dailyseventy.domain.data.database.model.DatabaseAya
import com.elsharif.dailyseventy.domain.data.database.model.DatabaseSora
import com.example.core.domain.quran.DomainQuranPage

@Dao
interface QuranDao {
    @Query("SELECT * FROM quran WHERE page = :page")
    suspend fun getAyatByPage(page: Int): List<DatabaseAya?>

    @Query(
        "SELECT sora as soraNumber," +
                " MIN(page) as startPage ," +
                "MAX(page) as endPage , " +
                "quran.sora_name_ar as arabicName," +
                " quran.sora_name_en  as englishName," +
                "MAX(aya_no) - MIN(aya_no) + 1 as ayaCount" +
                " FROM quran WHERE sora = :soraNumber"
    )
    suspend fun getSoraByNumber(soraNumber: Int): DatabaseSora


    @Query("SELECT * FROM quran WHERE quran.aya_text_emlaey LIKE '%' || :keyword || '%'")
    suspend fun getAyaBySubText(keyword: String?): List<DatabaseAya?>

    @Query("SELECT jozz FROM quran WHERE page = :pageNumber")
    suspend fun getJozzByNumberByPage(pageNumber: Int): Int



    @Query(
        "SELECT sora as soraNumber," +
                " MIN(page) as startPage ," +
                "MAX(page) as endPage ," +
                "quran.sora_name_ar as arabicName," +
                "quran.sora_name_en as englishName," +
                "MAX(aya_no) - MIN(aya_no) + 1 as ayaCount" +
                  " FROM quran WHERE page = :page"
    )
    suspend fun getSoraByPageNumber(page: Int): DatabaseSora


    @Query(
        "SELECT page as pageNumber," +
                "sora_name_ar as soraNameAr," +
                "sora_name_en as soraNameEn," +
                "sora as soraNo," +
                "0 as isBookmark" +
                " FROM quran WHERE page = :page"
    )
    suspend fun getPageData(page: Int): DomainQuranPage

}