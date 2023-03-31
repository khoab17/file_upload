package com.sharjahuniversity.type2dm_poc.ui.view

import android.content.Context
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
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
import com.sharjahuniversity.type2dm_poc.R
import com.sharjahuniversity.type2dm_poc.data.model.UserProfileData
import com.sharjahuniversity.type2dm_poc.data.model.responseModel.AuthResponseDataUser
import com.sharjahuniversity.type2dm_poc.ui.component.DialogWithOneButtonAndOneText
import com.sharjahuniversity.type2dm_poc.ui.theme.*
import com.sharjahuniversity.type2dm_poc.ui.viewmodel.PersonalDetailsInputScreenViewModel
import com.sharjahuniversity.type2dm_poc.utils.Constants
import com.sharjahuniversity.type2dm_poc.utils.DataState
import com.sharjahuniversity.type2dm_poc.utils.UserStatus
import com.sharjahuniversity.type2dm_poc.utils.Utils

class TextFieldState() {
    var text: String by mutableStateOf("")
    var surnameState: String by mutableStateOf("")
    var mobileNumberState: String by mutableStateOf("")
    var ageState: String by mutableStateOf("")
    var edLevelState: String by mutableStateOf("")
    var incomeState: String by mutableStateOf("")
    var occupationState: String by mutableStateOf("")
    var organisationState: String by mutableStateOf("")
}


@Composable
fun PersonalDetailsInputScreen(
    mActivity: MainActivity,
    authResponseDataUser: AuthResponseDataUser,
    personalDetailsInputScreenViewModel: PersonalDetailsInputScreenViewModel?
) {

    val name by remember {
        mutableStateOf(authResponseDataUser.name)
    }
    val email by remember {
        mutableStateOf(authResponseDataUser.email)
    }
    var gender by remember {
        mutableStateOf(Constants.Genders[0])
    }
    var showGenders by remember {
        mutableStateOf(false)
    }

    var showWaitingDialog by remember {
        mutableStateOf(false)
    }

    var stateTextFields = remember { TextFieldState() }

    personalDetailsInputScreenViewModel?.apply {
        userDataUploadResponse.observe(mActivity) {
            when (it) {
                is DataState.Success -> if (it.data.success) {
                    if (it.data.userDataUploadResponseData.userDataUploadResponseDataUser.status == UserStatus.Pending.value)
                        showWaitingDialog = true
                }
                else -> {}
            }
        }
    }
    Type2DMPocTheme {
        val mContext = LocalContext.current

        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            content = {

                if (showWaitingDialog) DialogWithOneButtonAndOneText(
                    text = stringResource(id = R.string.wait_till_approve_dialog_text),
                    buttonText = stringResource(id = R.string.ok),
                    buttonClick = { mActivity.finish() }) {
                    mActivity.finish()
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(White)
                        .padding(paddingValues = it)
                ) {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .padding(horizontal = 40.dp)
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        Box(modifier = Modifier.height(40.dp))
                        Text(
                            text = stringResource(R.string.sign_up),
                            color = DefaultTitleColor,
                            fontWeight = FontWeight(600),
                            fontSize = 28.sp,
                            lineHeight = 46.sp,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        Text(
                            text = stringResource(R.string.sign_up_subtitle),
                            color = DefaultSubtitleColor,
                            fontWeight = FontWeight(400),
                            fontSize = 14.sp,
                            lineHeight = 26.sp,
                            modifier = Modifier.padding(bottom = 30.dp)
                        )

                        GetTextCommon(stringResource(R.string.name))

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

                        GetTextCommon(stringResource(id = R.string.email))

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 18.dp)
                                .wrapContentHeight()
                                .background(DisabledBackgroundColor)
                                .clip(RoundedCornerShapes.small)
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

                        GetTextCommon(stringResource(id = R.string.phone))

                        TextFieldCommon(stateTextFields, "mobileNumber", KeyboardType.Phone)

                        //new fields are added starts

                        GetTextCommon("Surname")

                        TextFieldCommon(stateTextFields, "surname", KeyboardType.Text)

                        GetTextCommon("Age")

                        TextFieldCommon(stateTextFields, "age", KeyboardType.Number)

                        GetTextCommon("Education Level")

                        TextFieldCommon(stateTextFields, "edLevel", KeyboardType.Text)

                        GetTextCommon("Income")

                        TextFieldCommon(stateTextFields, "income", KeyboardType.Number)

                        GetTextCommon("Occupation")

                        TextFieldCommon(stateTextFields, "occupation", KeyboardType.Text)

                        GetTextCommon("Organisation")

                        TextFieldCommon(stateTextFields, "organisation", KeyboardType.Text)

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
                                        .border(1.dp, DefaultBorderColor, RoundedCornerShapes.small)
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

                                    if(stateTextFields.ageState.isEmpty()) {
                                        mToast(mContext, "Please enter the Age")
                                    }

                                    if(stateTextFields.mobileNumberState.isEmpty()) {
                                        mToast(mContext, "Please enter the Phone number")
                                    }

                                    if(stateTextFields.ageState.isNotEmpty()
                                        && stateTextFields.mobileNumberState.isNotEmpty()) {

                                        val user = UserProfileData(
                                            name = name,
                                            email = email,
                                            phone = stateTextFields.mobileNumberState,
                                            gender = gender,
                                            surname = stateTextFields.surnameState,
                                            age = stateTextFields.ageState.toDouble(),
                                            income = if(stateTextFields.incomeState.isEmpty()) {0.0} else{stateTextFields.incomeState.toDouble()},
                                            occupation = stateTextFields.occupationState,
                                            organization = stateTextFields.organisationState,
                                            education_level = stateTextFields.edLevelState,
                                            status = UserStatus.Pending.value

                                        )

                                        personalDetailsInputScreenViewModel?.uploadUser(
                                            user,
                                            Utils.getUserId(mActivity.applicationContext)!!
                                        )

                                    }


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
        )
    }
}


// Function to generate a Toast
private fun mToast(context: Context, text: String){
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}

@Composable
fun TextFieldCommon(
    state: TextFieldState = remember { TextFieldState() },
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
                value = getTextFieldInitText(state, type),
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
fun getTextFieldInitText(
    state: TextFieldState = remember { TextFieldState() },
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
fun GetTextCommon(text: String) {
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
fun PersonalDetailsInputScreen2Preview() {
    Type2DMPocTheme {
        /*PersonalDetailsInputScreen(
            MainActivity(),
            AuthResponseUser()
            null
        )*/
    }
}
