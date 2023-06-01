package com.dzakdzaks.movieapp.ui.screen.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun TextChip(modifier: Modifier = Modifier, text: String) {
    Text(
        modifier = modifier
            .wrapContentHeight()
            .clip(RoundedCornerShape(6.dp))
            .background(MaterialTheme.colorScheme.inverseOnSurface)
            .padding(horizontal = 4.dp, vertical = 2.dp),
        text = text,
        style = MaterialTheme.typography.bodyMedium
    )
}