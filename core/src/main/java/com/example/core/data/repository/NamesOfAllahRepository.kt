package com.example.core.data.repository

import com.example.core.data.datasource.NamesOfAllahDatasource

class NamesOfAllahRepository(private val datasource : NamesOfAllahDatasource) {
    fun getNamesOfAllah()  = datasource.getNamesOfAllah()

}
