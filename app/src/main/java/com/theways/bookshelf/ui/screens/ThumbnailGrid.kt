package com.theways.bookshelf.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.theways.bookshell.R


@Composable
fun BookListScreen(
    thumbnails: List<String>,
    modifier: Modifier = Modifier
) {
//    Text(
//        text = "${results.kind}: ${results.totalItems} books retrieved.",
//        modifier = modifier.verticalScroll(state = ScrollState(0), enabled = true)
//    )
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier.padding(4.dp),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center,
    ) {
        items(thumbnails) {
            BookThumbnail(thumbnail = it)
        }
    }
}

@Composable
private fun BookThumbnail(
    thumbnail: String,
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(thumbnail)
            .error(R.drawable.ic_broken_image)
            .placeholder(R.drawable.loading_img)
            .build(),
        contentDescription = null,
        modifier = modifier.padding(4.dp),
        contentScale = ContentScale.Crop
    )
}