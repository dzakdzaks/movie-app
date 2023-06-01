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
fun rememberBrokenImage(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "broken_image",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f
        ).apply {
            path(
                fill = SolidColor(Color.LightGray),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(5f, 21f)
                quadToRelative(-0.825f, 0f, -1.413f, -0.587f)
                quadTo(3f, 19.825f, 3f, 19f)
                verticalLineTo(5f)
                quadToRelative(0f, -0.825f, 0.587f, -1.413f)
                quadTo(4.175f, 3f, 5f, 3f)
                horizontalLineToRelative(14f)
                quadToRelative(0.825f, 0f, 1.413f, 0.587f)
                quadTo(21f, 4.175f, 21f, 5f)
                verticalLineToRelative(14f)
                quadToRelative(0f, 0.825f, -0.587f, 1.413f)
                quadTo(19.825f, 21f, 19f, 21f)
                close()
                moveToRelative(1f, -8.425f)
                lineToRelative(3.3f, -3.3f)
                quadToRelative(0.15f, -0.15f, 0.325f, -0.213f)
                quadTo(9.8f, 9f, 10f, 9f)
                reflectiveQuadToRelative(0.375f, 0.062f)
                quadToRelative(0.175f, 0.063f, 0.325f, 0.213f)
                lineToRelative(3.3f, 3.3f)
                lineToRelative(3.3f, -3.3f)
                quadToRelative(0.15f, -0.15f, 0.325f, -0.213f)
                quadTo(17.8f, 9f, 18f, 9f)
                reflectiveQuadToRelative(0.375f, 0.062f)
                quadToRelative(0.175f, 0.063f, 0.325f, 0.213f)
                lineToRelative(0.3f, 0.3f)
                verticalLineTo(5f)
                horizontalLineTo(5f)
                verticalLineToRelative(6.575f)
                close()
                moveTo(5f, 19f)
                horizontalLineToRelative(14f)
                verticalLineToRelative(-6.6f)
                lineToRelative(-1f, -1f)
                lineToRelative(-3.3f, 3.3f)
                quadToRelative(-0.125f, 0.125f, -0.312f, 0.2f)
                quadToRelative(-0.188f, 0.075f, -0.388f, 0.075f)
                reflectiveQuadToRelative(-0.375f, -0.075f)
                quadToRelative(-0.175f, -0.075f, -0.325f, -0.2f)
                lineTo(10f, 11.4f)
                lineToRelative(-3.3f, 3.3f)
                quadToRelative(-0.15f, 0.15f, -0.325f, 0.212f)
                quadToRelative(-0.175f, 0.063f, -0.375f, 0.063f)
                reflectiveQuadToRelative(-0.375f, -0.063f)
                quadTo(5.45f, 14.85f, 5.3f, 14.7f)
                lineToRelative(-0.3f, -0.3f)
                close()
                moveToRelative(0f, 0f)
                verticalLineToRelative(-6.6f)
                verticalLineToRelative(2f)
                verticalLineTo(5f)
                verticalLineToRelative(9.4f)
                close()
            }
        }.build()
    }
}