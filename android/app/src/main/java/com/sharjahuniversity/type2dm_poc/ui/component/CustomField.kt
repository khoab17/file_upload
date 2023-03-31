package com.sharjahuniversity.type2dm_poc.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CustomField(
    modifier: Modifier = Modifier,
    text:String,
    label: String,
    leadingIcon: @Composable (()->Unit)?=null,
    onChange: (String) -> Unit = {},
    fontSize:Int = 18,
    onFocusedColor:Color = Color.Gray,
    isEnable: Boolean = true
){
    OutlinedTextField(value = text, onValueChange = onChange,
        modifier = modifier
            .fillMaxWidth()
            .height(160.dp)
            .verticalScroll(rememberScrollState()),
        leadingIcon = leadingIcon,
        textStyle = TextStyle(fontSize = fontSize.sp),
        label = {
            Text(text = label)
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = onFocusedColor,
            focusedLabelColor = onFocusedColor,
        ) ,
        singleLine = false,
        maxLines = 5,
        enabled = isEnable
    )
}

@Preview
@Composable
fun PreviewCustomField(){
    Surface(color = Color.White) {
        CustomField(
            modifier = Modifier.padding(16.dp),
            text = "",
            label = "Feedback",
            fontSize = 18,
            isEnable = true
        )
    }
}