package com.example.core.data.repository

import com.example.core.data.datasource.AzkarDatasource

class AzkarRepository(private val datasource: AzkarDatasource) {
    fun getAzkarNames() = datasource.getAzkarNames()
    fun getAzkarOfCategory(zekrName: String) = datasource.getAzkarOfCategory(zekrName)
}