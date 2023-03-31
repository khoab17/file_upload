package com.sharjahuniversity.type2dm_poc.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sharjahuniversity.type2dm_poc.R
import com.sharjahuniversity.type2dm_poc.ui.theme.RoundedCornerShapes

@Composable
fun IntroductionCard(
    shape: RoundedCornerShape = RoundedCornerShapes.medium as RoundedCornerShape
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(
                    vertical = 12.dp
                )
        ) {
            Box(
                modifier = Modifier
                    .padding(
                        30.dp
                    ),
                contentAlignment = Alignment.Center,
            ) {

                Text(
                    text = stringResource(id = R.string.intro_text),
                    textAlign = TextAlign.Justify,
                )

            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    space = 20.dp,
                    alignment = Alignment.CenterHorizontally
                ),
                modifier = Modifier
                    .padding(
                        bottom = 16.dp,
                        top = 4.dp
                    )
                    .fillMaxWidth()
            ) {

            }
        }
    }


}


@Composable
@Preview
fun IntroductionCardPreview(
    shape: RoundedCornerShape = RoundedCornerShapes.medium as RoundedCornerShape
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(
                    vertical = 12.dp
                )
        ) {
            Box(
                modifier = Modifier
                    .padding(
                        30.dp
                    ),
                contentAlignment = Alignment.Center,
            ) {

                Text(
                    text = stringResource(id = R.string.intro_text),
                    textAlign = TextAlign.Justify,
                )

            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    space = 20.dp,
                    alignment = Alignment.CenterHorizontally
                ),
                modifier = Modifier
                    .padding(
                        bottom = 16.dp,
                        top = 4.dp
                    )
                    .fillMaxWidth()
            ) {

            }
        }
    }

}