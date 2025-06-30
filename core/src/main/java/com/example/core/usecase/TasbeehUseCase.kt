package com.example.core.usecase

import com.example.core.data.repository.TasbeehRepository


class IncreaseTasbeehUseCase(private val repository: TasbeehRepository) {
    operator fun invoke() = repository.increaseTasbeeh()
}

class ResetTasbeehUseCase(private val repository: TasbeehRepository) {
     operator fun invoke() = repository.resetTasbeeh()
}

class GetTasbeehCountUseCase(private val repository: TasbeehRepository) {
    operator fun invoke() = repository.getTasbeehCount()
}