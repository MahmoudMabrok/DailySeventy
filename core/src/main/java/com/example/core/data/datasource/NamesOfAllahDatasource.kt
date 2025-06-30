package com.example.core.data.datasource

import com.example.core.domain.DomainNameOfAllah
import kotlinx.coroutines.flow.Flow

interface NamesOfAllahDatasource {
    fun getNamesOfAllah(): Flow<List<DomainNameOfAllah>>
}
