package com.example.core.usecase

import com.example.core.data.repository.AzkarRepository
import kotlinx.coroutines.flow.single

class GetAzkarNamesUseCase(private val repository: AzkarRepository) {
    operator fun invoke() = repository.getAzkarNames()
}

class GetAzkarOfCategoryUseCase(private val repository: AzkarRepository) {
    operator fun invoke(zekrName: String) = repository.getAzkarOfCategory(zekrName)
}

class GetRandomZekrUseCase(private val repository: AzkarRepository) {
    suspend operator fun invoke() =
        repository.getAzkarOfCategory(repository.getAzkarNames().single().random().zekrName).single().first()
}