package com.sharjahuniversity.type2dm_poc.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sharjahuniversity.type2dm_poc.BuildConfig
import com.sharjahuniversity.type2dm_poc.R
import com.sharjahuniversity.type2dm_poc.ui.theme.Type2DMPocTheme
import com.sharjahuniversity.type2dm_poc.utils.Constants

@Composable
fun SplashScreen(alphaAnim: Float) {
    Type2DMPocTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .alpha(alphaAnim),

            content = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f)
                        .padding(paddingValues = it),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(
                            id = R.drawable.ic_townhall_colored
                        ),
                        contentDescription = Constants.FitLabIcon
                    )

                }
                Column(
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()

                ) {
                    Text(
                        text = String.format(Constants.Version, BuildConfig.VERSION_NAME),
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400),
                        lineHeight = 12.sp,
                        modifier = Modifier.padding(bottom = 36.dp)
                    )
                }
            }
        )
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen(alphaAnim = 1f)
}