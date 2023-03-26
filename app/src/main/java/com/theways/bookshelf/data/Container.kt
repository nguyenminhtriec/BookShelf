package com.theways.bookshelf.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.theways.bookshelf.network.BookApiSevice
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

interface Container {
    val bookRepository : Repository
}

class BookContainer : Container {

    private val BASE_URL = "https://www.googleapis.com/books/v1/volumes/"

    //private val QUERY_PARAM = "q"
    //val uri = Uri.parse(BASE_URL).buildUpon().appendQueryParameter(QUERY_PARAM, "Vietnam").build()
    //val url = uri.toString()

    private val json = Json { ignoreUnknownKeys = true } // JsonBuilder instead of Constructor

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: BookApiSevice by lazy {
        retrofit.create(BookApiSevice::class.java)
    }

    override val bookRepository: Repository by lazy {
        BookRepository(retrofitService)
    }

}