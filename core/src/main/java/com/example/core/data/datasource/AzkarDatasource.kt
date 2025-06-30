package com.example.core.data.datasource

import com.example.core.domain.azkar.DomainZekr
import com.example.core.domain.azkar.DomainZekrType
import kotlinx.coroutines.flow.Flow

interface AzkarDatasource {
    fun getAzkarOfCategory(zekrCategory: String): Flow<List<DomainZekr>>
    fun getAzkarNames(): Flow<List<DomainZekrType>>
}