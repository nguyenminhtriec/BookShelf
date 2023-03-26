package com.theways.bookshelf.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageLink(
    @SerialName ("smallThumbnail") val smallThumbnail: String = "",
    @SerialName("thumbnail") val thumbnail: String = "",
)

@Serializable
data class VolumeInfo(
    //@SerialName("title") val title: String,
    //@SerialName("subtitle") val subtitle: String,
    //@SerialName("authors") val authors: List<String>,
    @SerialName("imageLinks") val imageLinks: ImageLink = ImageLink()
)

@Serializable
data class BookItem(
    val id: String = "",
    val selfLink: String = "",
    val volumeInfo: VolumeInfo = VolumeInfo()
)

@Serializable
data class Grand(
    val kind: String,
    val totalItems: Int,
    val items: List<BookItem>,
)
