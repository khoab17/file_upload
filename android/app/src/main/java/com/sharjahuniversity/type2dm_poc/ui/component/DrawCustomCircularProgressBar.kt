package com.sharjahuniversity.type2dm_poc.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.sharjahuniversity.type2dm_poc.data.model.CircularProgressbar
import com.sharjahuniversity.type2dm_poc.utils.Utils

@Composable
fun DrawCustomCircularProgressbar(circularProgressbar: CircularProgressbar) {
    val progress = Utils.animateFloatValue(value = circularProgressbar.value)
    Canvas(
        modifier = Modifier
            .size(circularProgressbar.radius)
    ) {
        drawArc(
            color = circularProgressbar.progressbarTrackerColor,
            startAngle = -90f,
            sweepAngle = 360f,
            useCenter = false,
            style = Stroke(
                10.dp.toPx(),
                cap = StrokeCap.Round
            )
        )
    }
    Canvas(modifier = Modifier.size(circularProgressbar.radius)) {
        drawArc(
            brush = circularProgressbar.progressbarColorBrush,
            startAngle = -90f,
            sweepAngle = 360 * (progress / circularProgressbar.totalValue),
            useCenter = false,
            style = Stroke(
                width = 8.dp.toPx(),
                cap = StrokeCap.Round
            )
        )
    }
}