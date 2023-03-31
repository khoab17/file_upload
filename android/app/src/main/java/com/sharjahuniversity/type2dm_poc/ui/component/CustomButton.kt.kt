package com.sharjahuniversity.type2dm_poc.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sharjahuniversity.type2dm_poc.ui.theme.RoundedCornerShapes

@Composable
fun CustomButton(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp).clip(RoundedCornerShapes.small),
    text:String,
    enabled :Boolean = false,
    onClick: ()->Unit,
    color: Color = Color.Blue
){
    Button(onClick = onClick ,
        enabled = enabled ,
        modifier = modifier.background(color, RoundedCornerShapes.small),
        colors = ButtonDefaults.buttonColors(backgroundColor = color)
    ) {
        Row(modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = text, color = Color.White, modifier =
            Modifier.padding(horizontal = 16.dp))
        }
    }
}


@Preview
@Composable
fun PreviewCustomButton(){
    CustomButton(text = "Let's Start", onClick = {})
}