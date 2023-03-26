package com.theways.bookshelf.network

import com.theways.bookshelf.data.BookItem
import com.theways.bookshelf.data.Grand
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryName

interface BookApiSevice {
    @GET("https://www.googleapis.com/books/v1/volumes/")
    suspend fun getBooks(@Query ("q") q: String, @Query("maxResults") maxResults: Int): Grand
}