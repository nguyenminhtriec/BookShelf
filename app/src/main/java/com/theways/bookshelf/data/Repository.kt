package com.theways.bookshelf.data

import com.theways.bookshelf.network.BookApiSevice

interface Repository {
    suspend fun getBooks(q: String, maxResults: Int): Grand
}

class BookRepository(private val bookApiService: BookApiSevice) : Repository {
    override suspend fun getBooks(q: String, maxResults: Int): Grand {
        return bookApiService.getBooks(q = q, maxResults = maxResults)
    }
}