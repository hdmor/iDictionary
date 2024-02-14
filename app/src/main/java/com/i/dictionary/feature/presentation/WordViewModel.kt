package com.i.dictionary.feature.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.i.dictionary.core.util.Resource
import com.i.dictionary.feature.domain.usecase.GetWord
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordViewModel @Inject constructor(private val getWord: GetWord) : ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> get() = _searchQuery

    private val _state = mutableStateOf(WordState())
    val state: State<WordState> get() = _state

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow get() = _eventFlow.asSharedFlow()

    private var searchJob: Job? = null

    fun onSearch(query: String) {
        _searchQuery.value = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(1500L)
            getWord(query).onEach {
                when (it) {
                    is Resource.Success -> _state.value = state.value.copy(wordItems = it.data ?: emptyList(), isLoading = false)

                    is Resource.Error -> {
                        _state.value = state.value.copy(wordItems = it.data ?: emptyList(), isLoading = false)
                        _eventFlow.emit(UIEvent.ShowSnackBar(it.message ?: "Unknown error"))
                    }

                    is Resource.Loading -> _state.value = state.value.copy(wordItems = it.data ?: emptyList(), isLoading = true)
                }
            }.launchIn(this)
        }
    }

    sealed class UIEvent {

        data class ShowSnackBar(val message: String) : UIEvent()
    }
}