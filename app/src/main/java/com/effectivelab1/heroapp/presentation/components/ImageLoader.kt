package com.effectivelab1.heroapp.presentation.components
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@Composable
fun ImageLoader(
    imageUrl: String?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
) {
    imageUrl?.let {
        Log.d("HeroImage", "Loading image from URL: $it")
        AsyncImage(
            model = it.replace("http://", "https://"),
            contentDescription = contentDescription,
            modifier = modifier,
            contentScale = contentScale,
        )
    } ?: run {
        Log.d("HeroImage", "Image URL is null")
    }
}