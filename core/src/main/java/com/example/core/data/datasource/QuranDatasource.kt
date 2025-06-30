package com.example.core.data.datasource

import com.example.core.domain.quran.DomainAya
import com.example.core.domain.quran.DomainAyaWithTafseer
import com.example.core.domain.quran.DomainBookmark
import com.example.core.domain.quran.DomainJozz
import com.example.core.domain.quran.DomainQuranPage
import com.example.core.domain.quran.DomainSora
import kotlinx.coroutines.flow.Flow

interface QuranDatasource {
  //  fun getAllQuranSuar(): Flow<List<DomainSora>>;
 //   fun getQuranPages(): Flow<List<DomainQuranPage>>;
   // fun getAllAjzaa(): Flow<List<DomainJozz>>;
    fun getQuranPageAyaWithTafseer(page: Int): Flow<List<DomainAyaWithTafseer>>
  //  fun togglePageBookmark(bookmark : DomainBookmark): Flow<Unit>
    fun getSoraByPageNumber(page: Int): Flow<DomainSora>
}
