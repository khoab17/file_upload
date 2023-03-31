package com.sharjahuniversity.type2dm_poc.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.sharjahuniversity.type2dm_poc.R
import com.sharjahuniversity.type2dm_poc.ui.theme.ConformationDialogButtonRoundedCornerShape
import com.sharjahuniversity.type2dm_poc.ui.theme.CustomBluePrimary
import com.sharjahuniversity.type2dm_poc.ui.theme.RoundedCornerShapes
import com.sharjahuniversity.type2dm_poc.ui.theme.White

@Composable
fun DialogWithOneButtonAndOneText(
    text: String,
    buttonText: String,
    buttonColor: Color = CustomBluePrimary,
    buttonClick: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        ),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(White, RoundedCornerShapes.medium)
                .clip(RoundedCornerShapes.medium)
                .padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 28.dp)
        ) {
            Text(
                text = text,
                fontSize = 18.sp,
                fontWeight = FontWeight(400),
                lineHeight = 26.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(Color.Transparent, RoundedCornerShapes.medium)
                    .fillMaxWidth()
                    .padding(bottom = 0.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        //.fillMaxWidth(1f)
                        .weight(0.5f)
                        .background(
                            buttonColor,
                            ConformationDialogButtonRoundedCornerShape
                        )
                        .clip(ConformationDialogButtonRoundedCornerShape)
                        .clickable {
                            buttonClick()
                        }
                ) {
                    Text(
                        text = buttonText,
                        fontSize = 16.sp,
                        fontWeight = FontWeight(600),
                        lineHeight = 20.sp,
                        color = White,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DialogWithOneButtonAndOneTextPreview() {
    DialogWithOneButtonAndOneText(
        text = stringResource(id = R.string.wait_till_approve_dialog_text),
        buttonText = stringResource(id = R.string.ok),
        buttonClick = {},
        onDismiss = {}
    )
}