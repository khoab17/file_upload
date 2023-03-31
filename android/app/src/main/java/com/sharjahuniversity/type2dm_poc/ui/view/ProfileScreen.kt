package com.sharjahuniversity.type2dm_poc.ui.view

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sharjahuniversity.type2dm_poc.R
import com.sharjahuniversity.type2dm_poc.data.model.FetchedUserProfileData
import com.sharjahuniversity.type2dm_poc.data.model.IconDetails
import com.sharjahuniversity.type2dm_poc.ui.component.BackgroundTopColoredBox
import com.sharjahuniversity.type2dm_poc.ui.component.HomeScreenTopBar
import com.sharjahuniversity.type2dm_poc.ui.theme.*
import com.sharjahuniversity.type2dm_poc.ui.viewmodel.ProfileScreenViewModel
import com.sharjahuniversity.type2dm_poc.utils.Constants
import com.sharjahuniversity.type2dm_poc.utils.NavigationItem
import com.sharjahuniversity.type2dm_poc.utils.Utils
import java.util.*

var user = mutableStateOf(
    FetchedUserProfileData(
        email = "email@email.com",
        id = "id",
        imageUrl = "",
        name = "Name of the user",
        phone = "0123456789",
        studentId = "studentId",
        gender = "Male",
        surname = "surname",
        age = 0.0,
        education_level = "ed_level",
        organization = "organization",
        occupation = "occupation",
        income = 0.0
    )
)

@Composable
fun ProfileScreen(
    mActivity: ComponentActivity,
    navController: NavHostController,
    profileScreenViewModel: ProfileScreenViewModel
) {
    val userName = "Name of the user"
    val studentId = "STD-001"
    val userEmail = "email@email.com"
    val profilePrivilege = "Student"
    val phone = "0123456789"
    val gender = "Male"
    val dob = "11-08-2022"
    val height = 120.5f
    val weight = 50f

    LaunchedEffect(Unit){
        profileScreenViewModel.apply {
            if (Utils.isInternetConnected(mActivity)) fetchUser(
                Utils.getUserId(
                    mActivity
                )!!
            )
            else getUser()
        }
    }
    Type2DMPocTheme {
        Scaffold(
            modifier = Modifier.fillMaxWidth(),
            content = {
                Log.d("padding:", it.toString())
                BackgroundTopColoredBox(
                    shape = BackgroundTopColoredShape.medium,
                    color = HomeScreenTopBoxBackgroundColor
                )
                Column(
                    modifier = Modifier
                        .verticalScroll(ScrollState(0))
                        .padding(start = 24.dp, top = 8.dp, end = 24.dp),
                ) {
                    HomeScreenTopBar()
                    Box(modifier = Modifier.height(16.dp))
                    Column(
                        modifier = Modifier
                            .background(White, RoundedCornerShapes.medium)
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .padding(horizontal = 30.dp)
                                .fillMaxWidth()
                        ) {
                            Box(
                                contentAlignment = Alignment.BottomEnd,
                                modifier = Modifier
                                    .padding(top = 40.dp, bottom = 12.dp)
                                    .wrapContentHeight()
                            ) {
                                Utils.loadIcon(
                                    icon = IconDetails(
                                        user.value.imageUrl,
                                        R.drawable.ic_profile_picture_24
                                    )
                                ).value?.asImageBitmap()
                                    ?.let { it1 ->
                                        Image(
                                            bitmap = it1,
                                            contentDescription = Constants.ProfilePicture,
                                            modifier = Modifier
                                                .clip(CircleShape)
                                                .size(140.dp)
                                                .animateContentSize(
                                                    animationSpec = TweenSpec()
                                                )
                                        )
                                    }
                                if (false) Box(
                                    modifier = Modifier
                                        .padding(end = 4.dp, bottom = 4.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_camera_profile_pic_edit),
                                        contentDescription = Constants.ProfilePicture,
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .size(34.dp)
                                    )
                                }
                            }
                            Text(
                                text = user.value.name,
                                fontSize = 20.sp,
                                fontWeight = FontWeight(400),
                                lineHeight = 30.sp,
                                textAlign = TextAlign.Center,
                                color = ProfileScreenNameColor
                            )
                            Text(
                                text = profilePrivilege,
                                fontSize = 14.sp,
                                fontWeight = FontWeight(400),
                                lineHeight = 21.sp,
                                textAlign = TextAlign.Center,
                                color = ProfileScreenDesignationColor,
                                modifier = Modifier.padding(bottom = 30.dp)
                            )
                        }
                        TextTitleSubtitleForProfileScreen(
                            stringResource(id = R.string.name), user.value.name
                        )

                        TextTitleSubtitleForProfileScreen(
                            stringResource(id = R.string.email), user.value.email
                        )

                        TextTitleSubtitleForProfileScreen(
                            stringResource(R.string.phone),
                            if (user.value.phone == "" || user.value.phone.isNullOrBlank() || user.value.phone.isEmpty()) "NA" else user.value.phone
                        )

                        TextTitleSubtitleForProfileScreen(
                            stringResource(id = R.string.surname), user.value.surname
                        )

                        TextTitleSubtitleForProfileScreen(
                            stringResource(id = R.string.age), user.value.age.toString()
                        )

                        TextTitleSubtitleForProfileScreen(
                            stringResource(id = R.string.education_level), user.value.education_level
                        )

                        TextTitleSubtitleForProfileScreen(
                            stringResource(id = R.string.income), user.value.income.toString()
                        )

                        TextTitleSubtitleForProfileScreen(
                            stringResource(id = R.string.occupation), user.value.occupation
                        )

                        TextTitleSubtitleForProfileScreen(
                            stringResource(id = R.string.organisation), user.value.organization
                        )

                        TextTitleSubtitleForProfileScreen(
                            stringResource(R.string.gender), user.value.gender
                        )
//                    TextTitleSubtitleForProfileScreen(
//                        stringResource(R.string.date_of_birth), user.value.dateOfBirth
//                    )
//                    TextTitleSubtitleForProfileScreen(
//                        stringResource(R.string.height),
//                        String.format(
//                            Locale.ENGLISH,
//                            stringResource(id = R.string.height_CM_string_format),
//                            user.value.height
//                        )
//                    )
//                    TextTitleSubtitleForProfileScreen(
//                        stringResource(R.string.weight),
//                        String.format(
//                            Locale.ENGLISH,
//                            stringResource(id = R.string.weight_KG_string_format),
//                            user.value.weight
//                        )
//                    )
                        Box(
                            modifier = Modifier
                                .padding(start = 30.dp, end = 30.dp, top = 30.dp, bottom = 10.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 18.dp)
                                    .wrapContentHeight()
                                    .background(CompleteButtonColor, RoundedCornerShapes.small)
                                    .clickable {
                                        navigate(NavigationItem.ProfileEdit, navController)
                                    }
                            ) {
                                Text(
                                    text = stringResource(R.string.edit_profile),
                                    color = White,
                                    fontWeight = FontWeight(600),
                                    fontSize = 14.sp,
                                    lineHeight = 14.sp,
                                    modifier = Modifier
                                        .wrapContentWidth()
                                        .padding(vertical = 18.dp),
                                )
                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .height(90.dp)
                            .fillMaxWidth()
                    )
                }
            }
        )
    }
}

@Composable
fun TextTitleSubtitleForProfileScreen(
    title: String,
    subtitle: String
) {
    var subtitleNew = ""

    if(title == stringResource(id = R.string.age)) {
        val intValue = subtitle.toDouble().toInt()
        subtitleNew = intValue.toString()
    } else{
        subtitleNew = subtitle
    }
    
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 30.dp)
    ) {
        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight(700),
            lineHeight = 14.sp,
            textAlign = TextAlign.Start,
            color = ProfileScreenItemTitleColor
        )
        Box(modifier = Modifier.height(4.dp))
        Text(
            text = subtitleNew,
            fontSize = 14.sp,
            fontWeight = FontWeight(400),
            lineHeight = 14.sp,
            textAlign = TextAlign.Start,
            color = ProfileScreenItemTitleColor
        )
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    /*ProfileScreen(
        mActivity = ComponentActivity(),
        navController = rememberNavController()
    )*/
}

@Preview(showBackground = true)
@Composable
fun TextTitleSubtitle() {
    TextTitleSubtitleForProfileScreen(
        title = "Name",
        subtitle = "Name of the user"
    )
}