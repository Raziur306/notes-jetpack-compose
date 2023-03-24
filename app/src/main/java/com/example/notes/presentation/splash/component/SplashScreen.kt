package com.example.notes.presentation.splash.component

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.example.notes.R
import com.example.notes.presentation.splash.SplashViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notes.presentation.splash.SplashState

@Composable
fun SplashScreen(viewModel: SplashViewModel = hiltViewModel(), navigate: (Boolean) -> Unit) {
    val state: State<SplashState> = viewModel.state
    val value: SplashState = state.value
    if (value.tokenExist && value.navTrigger) {
        LaunchedEffect(Unit) {
            navigate(true)
        }

    } else if (!value.tokenExist && value.navTrigger) {
        LaunchedEffect(Unit) {
            navigate(false)
        }
    }


    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(data = R.drawable.splash_icon)
                    .apply(block = fun ImageRequest.Builder.() {
                        size(Size.ORIGINAL)
                    }).build(),
                imageLoader = imageLoader
            ),
            contentDescription = null,
        )
    }
}