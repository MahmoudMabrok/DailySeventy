package com.example.core.data.repository

import com.example.core.data.datasource.QuranDatasource
import com.example.core.domain.quran.DomainBookmark
import com.example.core.domain.quran.DomainJozz
import com.example.core.domain.quran.DomainQuranPage
import com.example.core.domain.quran.DomainSora
import kotlinx.coroutines.flow.Flow

class QuranRepository(private val datasource: QuranDatasource) {

    //fun getAllQuranSuar(): Flow<List<DomainSora>> = datasource.getAllQuranSuar()
   // fun getQuranPages(): Flow<List<DomainQuranPage>> = datasource.getQuranPages()
    fun getSoraByPageNumber(page: Int) = datasource.getSoraByPageNumber(page)
    fun getQuranPageAyaWithTafseer(page: Int) = datasource.getQuranPageAyaWithTafseer(page)
   // fun getAllAjzaa(): Flow<List<DomainJozz>> = datasource.getAllAjzaa()

}
