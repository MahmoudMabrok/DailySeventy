package com.example.core.usecase

import com.example.core.data.repository.NamesOfAllahRepository

class GetNamesOfAllahUseCase(private val namesOfAllahRepository : NamesOfAllahRepository){
    operator fun invoke() = namesOfAllahRepository.getNamesOfAllah()
}