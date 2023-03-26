package com.theways.bookshelf

import android.app.Application
import com.theways.bookshelf.data.BookContainer
import com.theways.bookshelf.data.Container

class BookApplication : Application() {

    lateinit var container: Container

    override fun onCreate() {
        super.onCreate()
        container = BookContainer()
    }
}