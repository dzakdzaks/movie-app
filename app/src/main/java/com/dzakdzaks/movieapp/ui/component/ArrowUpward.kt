package com.dzakdzaks.movieapp.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

@Composable
fun rememberArrowUpward(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "arrow_upward",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(12f, 20f)
                quadToRelative(-0.425f, 0f, -0.712f, -0.288f)
                quadTo(11f, 19.425f, 11f, 19f)
                verticalLineTo(7.825f)
                lineTo(6.125f, 12.7f)
                quadToRelative(-0.3f, 0.3f, -0.713f, 0.3f)
                quadTo(5f, 13f, 4.7f, 12.7f)
                reflectiveQuadToRelative(-0.3f, -0.7f)
                quadToRelative(0f, -0.4f, 0.3f, -0.7f)
                lineToRelative(6.6f, -6.6f)
                quadToRelative(0.15f, -0.15f, 0.325f, -0.213f)
                quadToRelative(0.175f, -0.062f, 0.375f, -0.062f)
                reflectiveQuadToRelative(0.388f, 0.062f)
                quadToRelative(0.187f, 0.063f, 0.312f, 0.213f)
                lineToRelative(6.6f, 6.6f)
                quadToRelative(0.3f, 0.3f, 0.3f, 0.7f)
                quadToRelative(0f, 0.4f, -0.3f, 0.7f)
                quadToRelative(-0.3f, 0.3f, -0.713f, 0.3f)
                quadToRelative(-0.412f, 0f, -0.712f, -0.3f)
                lineTo(13f, 7.825f)
                verticalLineTo(19f)
                quadToRelative(0f, 0.425f, -0.287f, 0.712f)
                quadTo(12.425f, 20f, 12f, 20f)
                close()
            }
        }.build()
    }
}