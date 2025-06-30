package com.elsharif.dailyseventy.domain.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("quran")
data class DatabaseAya(
    @PrimaryKey val id: Int,
    @ColumnInfo("jozz")  val jozz: Int,
    @ColumnInfo("sora")  val sora: Int,
    @ColumnInfo("sora_name_en")  val soraNameEn: String,
    @ColumnInfo("sora_name_ar")  val soraNameAr: String,
    @ColumnInfo("page")  val page: Int,
    @ColumnInfo("line_start")  val lineStart: Int,
    @ColumnInfo("line_end")  val lineEnd: Int,
    @ColumnInfo("aya_no")  val ayaNo: Int,
    @ColumnInfo("aya_text")  val ayaText: String,
    @ColumnInfo("aya_text_emlaey")  val ayaTextEmlaey: String,
)
