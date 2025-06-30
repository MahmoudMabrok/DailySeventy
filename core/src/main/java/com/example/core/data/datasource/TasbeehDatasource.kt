package com.example.core.data.datasource

import kotlinx.coroutines.flow.Flow

interface TasbeehDatasource {
    fun increaseTasbeeh(): Flow<Boolean>

    fun resetTasbeeh():  Flow<Boolean>
    fun getTasbeehCount():  Flow<Int>
}