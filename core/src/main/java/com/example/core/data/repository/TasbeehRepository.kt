package com.example.core.data.repository

import com.example.core.data.datasource.TasbeehDatasource

class TasbeehRepository(private val datasource: TasbeehDatasource) {
    fun increaseTasbeeh() = datasource.increaseTasbeeh()

    fun resetTasbeeh() = datasource.resetTasbeeh()
    fun getTasbeehCount() = datasource.getTasbeehCount()
}