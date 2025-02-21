package com.elsharif.dailyseventy.presentation.zekr

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elsharif.dailyseventy.domain.repository.ZekrRepository
import com.elsharif.dailyseventy.presentaion.zekr.ZekkrEvent
import com.elsharif.dailyseventy.presentaion.zekr.ZekkrState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ZekkrViewModel @Inject constructor(
    private val zekrRepository: ZekrRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ZekkrState()) // Holds UI state
    val state: StateFlow<ZekkrState> = _state.asStateFlow()

    init {
        loadAzkaar() // Load data on initialization
    }

    // Handles UI events
    fun onEvent(event: ZekkrEvent) {
        when (event) {
            is ZekkrEvent.SelectCategory -> selectCategory(event.category)
            ZekkrEvent.LoadAzkaar -> loadAzkaar()
        }
    }

    private fun loadAzkaar() {
        viewModelScope.launch {
            zekrRepository.azkaarList.collect { azkaar ->
                _state.update { it.copy(azkaar = azkaar) }
            }
        }
    }

    private fun selectCategory(category: String?) {
        viewModelScope.launch {
            val filteredAzkaar = if (category.isNullOrEmpty()) {
                emptyList() // If no category is selected, return an empty list
            } else {
                zekrRepository.getZekrByCategory(category) // Fetch filtered list
            }
            _state.update {
                it.copy(
                    selectedCategory = category,
                    azkaar = filteredAzkaar
                )
            }
        }
    }
}
