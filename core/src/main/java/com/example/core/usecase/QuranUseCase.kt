package com.example.core.usecase

import com.example.core.data.repository.QuranRepository
import com.example.core.domain.quran.DomainBookmark

/*
class GetAllQuranSuarUseCase(private val repository: QuranRepository) {
    operator fun invoke() = repository.getAllQuranSuar()
}

class GetAllQuranAjzaaUseCase(private val repository: QuranRepository) {
    operator fun invoke() = repository.getAllAjzaa()
}

class GetAllQuranPagesUseCase(private val repository: QuranRepository) {
    operator fun invoke() = repository.getQuranPages()
}


*/

class GetQuranPageAyaWithTafseerUseCase(private val repository : QuranRepository){
    operator fun invoke(page:Int) = repository.getQuranPageAyaWithTafseer(page)
}



class GetSoraByPageNumberUseCase(private val repository : QuranRepository){
    operator fun invoke(page: Int) = repository.getSoraByPageNumber(page)
}