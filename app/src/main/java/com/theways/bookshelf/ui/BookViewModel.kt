package com.theways.bookshelf.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.theways.bookshelf.BookApplication
import com.theways.bookshelf.data.Grand
import com.theways.bookshelf.data.Repository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


interface BookUiState {
    data class Success(val results: Grand) : BookUiState
    object Loading : BookUiState
    object Error : BookUiState
    object NoState: BookUiState
}

class BookViewModel(private val bookRepository: Repository) : ViewModel() {

    var uiState: BookUiState by mutableStateOf(BookUiState.NoState)
        private set

    var queryString by mutableStateOf("")
        private set

    var errorMessage: String = ""

    val bookIds: MutableList<String> = mutableListOf() // for test purpose
    val thumbnails: MutableList<String> = mutableListOf()

    fun updateQueryString(newQuery: String) {
        queryString = newQuery
        bookIds.clear() // for test purpose
        thumbnails.clear()
    }

    fun getBooks(q: String) {
        viewModelScope.launch {
            uiState = BookUiState.Loading
            try {
                val results = bookRepository.getBooks(q = q, maxResults = 20)
                uiState = BookUiState.Success(results = results)

                results.items.forEach {
                    bookIds.add(it.id)
                    thumbnails.add(it.volumeInfo.imageLinks.thumbnail.replace("http", "https"))
                }
            } catch (e: HttpException) {
                uiState = BookUiState.Error
                errorMessage = "No internet service availlable. ${e.message}"
            } catch (e: IOException) {
                uiState = BookUiState.Error
                errorMessage = "Datasource not available. ${e.message?.uppercase()}"
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val bookApplication = (this[APPLICATION_KEY] as BookApplication)
                val bookRepository = bookApplication.container.bookRepository
                BookViewModel(bookRepository = bookRepository)
            }
        }
    }
}