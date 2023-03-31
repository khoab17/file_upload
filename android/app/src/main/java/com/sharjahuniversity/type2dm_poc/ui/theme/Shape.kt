package com.sharjahuniversity.type2dm_poc.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)

val AddItemDialogButtonRoundedCornerShape = RoundedCornerShape(16.dp)
val ConformationDialogButtonRoundedCornerShape = RoundedCornerShape(16.dp)

val RoundedCornerShapes = Shapes(
    small = RoundedCornerShape(10.dp),
    medium = RoundedCornerShape(20.dp),
    large = RoundedCornerShape(40.dp)
)

val BackgroundTopColoredShape = Shapes(
    small = RoundedCornerShape(
        bottomStart = 20.dp, bottomEnd = 20.dp
    ),
    medium = RoundedCornerShape(
        bottomStart = 40.dp, bottomEnd = 40.dp
    )
)