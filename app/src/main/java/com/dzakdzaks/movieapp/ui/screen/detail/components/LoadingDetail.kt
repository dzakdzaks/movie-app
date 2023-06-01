package com.dzakdzaks.movieapp.ui.screen.detail.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dzakdzaks.movieapp.ui.component.LoadingAnimation

@Composable
fun LoadingDetail() {
    Box(modifier = Modifier.fillMaxWidth()) {
        LoadingAnimation(modifier = Modifier.align(Alignment.Center))
    }
}