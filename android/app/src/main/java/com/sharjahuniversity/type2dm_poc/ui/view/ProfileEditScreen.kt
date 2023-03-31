package com.sharjahuniversity.type2dm_poc.ui.view

import androidx.activity.ComponentActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sharjahuniversity.type2dm_poc.R
import com.sharjahuniversity.type2dm_poc.data.model.UserProfileData
import com.sharjahuniversity.type2dm_poc.ui.component.DefaultTopBar
import com.sharjahuniversity.type2dm_poc.ui.theme.*
import com.sharjahuniversity.type2dm_poc.ui.viewmodel.ProfileEditScreenViewModel
import com.sharjahuniversity.type2dm_poc.utils.Constants
import com.sharjahuniversity.type2dm_poc.utils.NavigationItem
import com.sharjahuniversity.type2dm_poc.utils.UserStatus
import com.sharjahuniversity.type2dm_poc.utils.Utils

class TextFieldStateProfileEdit() {
    var text: String by mutableStateOf("")
    var surnameState: String by mutableStateOf(user.value.surname)
    var mobileNumberState: String by mutableStateOf(user.value.phone)
    var ageState: String by mutableStateOf(getFormattedText(user.value.age.toString()))
    var edLevelState: String by mutableStateOf(user.value.education_level)
    var incomeState: String by mutableStateOf(user.value.income.toString())
    var occupationState: String by mutableStateOf(user.value.occupation)
    var organisationState: String by mutableStateOf(user.value.organization)

    fun getFormattedText(text: String):String {
        var formattedText = ""

        val intValue = text.toDouble().toInt()
        formattedText = intValue.toString()

        return formattedText
    }
}

@Composable
fun ProfileEditScreen(
    mActivity: ComponentActivity,
    navController: NavHostController,
    profileEditScreenViewModel: ProfileEditScreenViewModel
) {

    val name by remember {
        mutableStateOf(user.value.name)
    }
    val email by remember {
        mutableStateOf(user.value.email)
    }
    var phone by remember {
        mutableStateOf(user.value.phone)
    }
    var surname by remember {
        mutableStateOf(user.value.surname)
    }
    var age by remember {
        mutableStateOf(user.value.age)
    }
    var income by remember {
        mutableStateOf(user.value.income)
    }
    var edLevel by remember {
        mutableStateOf(user.value.education_level)
    }
    var occupation by remember {
        mutableStateOf(user.value.occupation)
    }
    var organisation by remember {
        mutableStateOf(user.value.organization)
    }
    var gender by remember {
        mutableStateOf(user.value.gender)
    }
    var showGenders by remember {
        mutableStateOf(false)
    }

    var stateTextFields = remember { TextFieldStateProfileEdit() }


    Type2DMPocTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            //.padding(8.dp)
            content = {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(White)
                        .padding(paddingValues = it)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 28.dp)
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        DefaultTopBar(
                            title = stringResource(R.string.edit_profile_title),
                            titleColor = Black,
                            iconTint = Black
                        ) {
                            navigate(NavigationItem.Profile, navController)
                        }
                        Column(
                            horizontalAlignment = Alignment.Start,
                            modifier = Modifier
                                .padding(horizontal = 0.dp)
                                .fillMaxSize()
                        ) {
                            Box(modifier = Modifier.height(16.dp))

                            Text(
                                text = stringResource(id = R.string.name),
                                color = DefaultTitleColor,
                                fontWeight = FontWeight(400),
                                fontSize = 14.sp,
                                lineHeight = 14.sp,
                                modifier = Modifier.padding(bottom = 14.dp)
                            )
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 18.dp)
                                    .wrapContentHeight()
                                    .clip(RoundedCornerShapes.small)
                                    .background(DisabledBackgroundColor)
                                    .border(1.dp, DefaultBorderColor, RoundedCornerShapes.small)
                            ) {
                                Text(
                                    text = name,
                                    color = DefaultTextFieldTextColor,
                                    fontWeight = FontWeight(400),
                                    fontSize = 14.sp,
                                    lineHeight = 14.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                )
                            }
                            Text(
                                text = stringResource(id = R.string.email),
                                color = DefaultTitleColor,
                                fontWeight = FontWeight(400),
                                fontSize = 14.sp,
                                lineHeight = 14.sp,
                                modifier = Modifier.padding(bottom = 14.dp)
                            )
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 18.dp)
                                    .wrapContentHeight()
                                    .clip(RoundedCornerShapes.small)
                                    .background(DisabledBackgroundColor)
                                    .border(1.dp, DefaultBorderColor, RoundedCornerShapes.small)
                            ) {
                                Text(
                                    text = email,
                                    color = DefaultTextFieldTextColor,
                                    fontWeight = FontWeight(400),
                                    fontSize = 14.sp,
                                    lineHeight = 14.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                )
                            }



                            TextFieldCommonProfileEdit(
                                stateTextFields,
                                "mobileNumber",
                                KeyboardType.Phone,
                            )

                            //new fields are added starts

                            GetTextCommonEditProfile("Surname")

                            TextFieldCommonProfileEdit(
                                stateTextFields,
                                "surname",
                                KeyboardType.Text
                            )

                            GetTextCommonEditProfile("Age")

                            TextFieldCommonProfileEdit(
                                stateTextFields,
                                "age",
                                KeyboardType.Number,
                            )

                            GetTextCommonEditProfile("Education Level")

                            TextFieldCommonProfileEdit(
                                stateTextFields,
                                "edLevel",
                                KeyboardType.Text
                            )

                            GetTextCommonEditProfile("Income")

                            TextFieldCommonProfileEdit(
                                stateTextFields,
                                "income",
                                KeyboardType.Number
                            )

                            GetTextCommonEditProfile("Occupation")

                            TextFieldCommonProfileEdit(
                                stateTextFields,
                                "occupation",
                                KeyboardType.Text
                            )

                            GetTextCommonEditProfile("Organisation")

                            TextFieldCommonProfileEdit(
                                stateTextFields,
                                "organisation",
                                KeyboardType.Text
                            )

                            //new fields are added ends


                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.Start,
                                    modifier = Modifier.weight(0.5f)
                                ) {
                                    Text(
                                        text = stringResource(R.string.gender),
                                        color = DefaultTitleColor,
                                        fontWeight = FontWeight(400),
                                        fontSize = 14.sp,
                                        lineHeight = 14.sp,
                                        modifier = Modifier.padding(bottom = 14.dp)
                                    )
                                    Row(
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(bottom = 18.dp)
                                            .wrapContentHeight()
                                            .clip(RoundedCornerShapes.small)
                                            .border(
                                                1.dp,
                                                DefaultBorderColor,
                                                RoundedCornerShapes.small
                                            )
                                            .clickable { showGenders = !showGenders }
                                            .onFocusChanged { showGenders = false }
                                    ) {
                                        Text(
                                            text = gender,
                                            color = DefaultTextFieldTextColor,
                                            fontWeight = FontWeight(400),
                                            fontSize = 14.sp,
                                            lineHeight = 14.sp,
                                            modifier = Modifier
                                                .wrapContentWidth()
                                                .padding(
                                                    top = 16.dp,
                                                    start = 16.dp,
                                                    end = 8.dp,
                                                    bottom = 16.dp
                                                ),
                                        )
                                        if (showGenders) {
                                            DropdownMenu(
                                                expanded = showGenders,
                                                onDismissRequest = { showGenders = false }
                                            ) {
                                                Constants.Genders.forEach {
                                                    DropdownMenuItem(
                                                        onClick = {
                                                            gender = it
                                                            showGenders = false
                                                        }
                                                    ) {
                                                        Text(text = it)
                                                    }
                                                }
                                            }
                                        }
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_down),
                                            contentDescription = Constants.DropDownIcon,
                                            modifier = Modifier
                                                .padding(end = 16.dp)
                                                .size(12.dp)
                                        )
                                    }
                                }
                                Box(modifier = Modifier.width(16.dp))

                            }


                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 18.dp, top = 8.dp)
                                    .wrapContentHeight()
                                    .background(CompleteButtonColor, RoundedCornerShapes.small)
                                    //.border(1.dp, DefaultBorderColor, RoundedCornerShapes.small)
                                    .clickable {
                                        val data = UserProfileData(
                                            name = name,
                                            email = email,
                                            phone = stateTextFields.mobileNumberState,
                                            gender = gender,
                                            surname = stateTextFields.surnameState,
                                            age = stateTextFields.ageState.toDouble(),
                                            income = stateTextFields.incomeState.toDouble(),
                                            occupation = stateTextFields.occupationState,
                                            organization = stateTextFields.organisationState,
                                            education_level = stateTextFields.edLevelState,
                                            status = UserStatus.Active.value
                                        )
                                        profileEditScreenViewModel.uploadUser(
                                            data,
                                            Utils.getUserId(mActivity)!!
                                        )
                                        navigate(NavigationItem.Profile, navController)
                                    }
                            ) {
                                Text(
                                    text = stringResource(R.string.complete),
                                    color = White,
                                    fontWeight = FontWeight(600),
                                    fontSize = 14.sp,
                                    lineHeight = 14.sp,
                                    modifier = Modifier
                                        .wrapContentWidth()
                                        .padding(vertical = 18.dp),
                                )
                            }
                            Box(modifier = Modifier.height(280.dp))
                        }
                    }

                }

            }

        )
    }
}


@Composable
fun TextFieldCommonProfileEdit(
    state: TextFieldStateProfileEdit = remember { TextFieldStateProfileEdit() },
    type: String, keyboardType: KeyboardType
) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 18.dp)
            .wrapContentHeight()
            .background(White)
            .clip(RoundedCornerShapes.small)
            .border(1.dp, DefaultBorderColor, RoundedCornerShapes.small)
    ) {
        Box(
            modifier = Modifier
            //.wrapContentWidth()
            //.padding(top = 8.dp, bottom = 8.dp, end = 8.dp)
            //.border(1.dp, AddItemQuantityBorderColor, RoundedCornerShapes.small)
        ) {
            BasicTextField(
                value = getTextFieldInitTextEditProfile(state, type),
                onValueChange = {
                    if (type == "surname") {
                        state.surnameState = it
                    } else if (type == "mobileNumber") {
                        state.mobileNumberState = it
                    } else if (type == "age") {
                        state.ageState = it
                    } else if (type == "edLevel") {
                        state.edLevelState = it
                    } else if (type == "income") {
                        state.incomeState = it
                    } else if (type == "occupation") {
                        state.occupationState = it
                    } else if (type == "organisation") {
                        state.organisationState = it
                    }

                },
                textStyle = TextStyle(
                    color = AddItemQuantityColor,
                    fontWeight = FontWeight(500),
                    fontSize = 14.sp,
                    lineHeight = 14.sp,
                    textAlign = TextAlign.Start,
                    textDirection = TextDirection.ContentOrLtr
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun getTextFieldInitTextEditProfile(
    state: TextFieldStateProfileEdit = remember { TextFieldStateProfileEdit() },
    type: String
): String {
    when (type) {
        "surname" -> {
            return state.surnameState
        }
        "mobileNumber" -> {
            return state.mobileNumberState
        }
        "age" -> {
            return state.ageState
        }
        "edLevel" -> {
            return state.edLevelState
        }
        "income" -> {
            return state.incomeState
        }
        "occupation" -> {
            return state.occupationState
        }
        "organisation" -> {
            return state.organisationState
        }
        else -> {
            return ""
        }
    }
}


@Composable
fun GetTextCommonEditProfile(text: String) {
    Text(
        text = text,
        color = DefaultTitleColor,
        fontWeight = FontWeight(400),
        fontSize = 14.sp,
        lineHeight = 14.sp,
        modifier = Modifier.padding(bottom = 14.dp)
    )
}

@Preview(showSystemUi = true)
@Composable
fun ProfileEditScreen2Preview() {
    Type2DMPocTheme {
        /*PersonalDetailsInputScreen(
            MainActivity(),
            AuthResponseUser()
            null
        )*/
    }
}
