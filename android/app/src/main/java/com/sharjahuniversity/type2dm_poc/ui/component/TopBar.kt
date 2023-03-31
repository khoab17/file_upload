package com.sharjahuniversity.type2dm_poc.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sharjahuniversity.type2dm_poc.R
import com.sharjahuniversity.type2dm_poc.data.model.IconDetails
import com.sharjahuniversity.type2dm_poc.ui.theme.Black
import com.sharjahuniversity.type2dm_poc.ui.view.user
import com.sharjahuniversity.type2dm_poc.utils.Constants
import com.sharjahuniversity.type2dm_poc.utils.Utils

//val userImageUrl = mutableStateOf("")

@Composable
fun HomeScreenTopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(vertical = 8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_townhall),
                contentDescription = Constants.FitLabIcon,
                modifier = Modifier
                    .height(40.dp)
                    .width(106.dp)
            )
            Utils.loadIcon(
                icon = IconDetails(
                    user.value.imageUrl,
                    R.drawable.ic_baseline_account_circle_24
                )
            ).value?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = Constants.ProfilePicture,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color.White,
                            shape = CircleShape
                        )
                        .clip(CircleShape)
                        .size(40.dp)
                )
            }
        }
    }
}

/*@Composable
fun FoodScreenTopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(vertical = 8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_fitlab_colored),
                contentDescription = Constants.FitLabIcon,
                modifier = Modifier.height(40.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_account_circle_24),
                contentDescription = Constants.ProfilePicture,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color.White,
                        shape = CircleShape
                    )
                    .clip(CircleShape)
                    .size(40.dp)
            )
        }
    }
}*/

@Composable
fun DefaultTopBar(
    title: String,
    titleColor: Color,
    iconTint: Color,
    onClickBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(vertical = 8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .clickable(onClick = onClickBack)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_button),
                    contentDescription = Constants.BackButton,
                    tint = iconTint,
                    modifier = Modifier
                        .rotate(if (LocalLayoutDirection.current == LayoutDirection.Rtl) 180f else 0f)
                        .height(24.dp)
                )
            }
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight(400),
                color = titleColor
            )
            Utils.loadIcon(
                icon = IconDetails(
                    user.value.imageUrl,
                    R.drawable.ic_baseline_account_circle_24
                )
            ).value?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = Constants.ProfilePicture,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color.White,
                            shape = CircleShape
                        )
                        .clip(CircleShape)
                        .size(40.dp)
                )
            }
        }
    }
}

/*@Composable
fun NavigationScreenTopBar(
    onClickBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(vertical = 8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_back_button),
                contentDescription = Constants.BackButton,
                modifier = Modifier
                    .height(24.dp)
                    .clickable(onClick = onClickBack)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_account_circle_24),
                contentDescription = Constants.ProfilePicture,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color.White,
                        shape = CircleShape
                    )
                    .clip(CircleShape)
                    .size(40.dp)
            )
        }
    }
}*/

@Preview(showSystemUi = true)
@Composable
fun TopBarPreview() {
    /*FoodScreenTopBar()
    HomeScreenTopBar()
    NavigationScreenTopBar (){}*/
    DefaultTopBar(title = "title", Black, Black) {}
}