package com.sharjahuniversity.type2dm_poc.ui.view

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.common.api.ApiException
import com.sharjahuniversity.type2dm_poc.R
import com.sharjahuniversity.type2dm_poc.data.remote.GoogleAuthResult
import com.sharjahuniversity.type2dm_poc.ui.component.GoogleSignInButton
import com.sharjahuniversity.type2dm_poc.ui.theme.CustomBluePrimary
import com.sharjahuniversity.type2dm_poc.ui.theme.Type2DMPocTheme
import com.sharjahuniversity.type2dm_poc.ui.viewmodel.GoogleSignInViewModel
import com.sharjahuniversity.type2dm_poc.utils.Constants
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(googleSignInViewModel: GoogleSignInViewModel) {
    Type2DMPocTheme() {
        val scope = rememberCoroutineScope()
        var text by remember { mutableStateOf<String?>(null) }
        val googleSignInFailed = stringResource(
            id = R.string.google_sign_in_failed
        )
        val signInRequestCode = 1
        val authResultLauncher =
            rememberLauncherForActivityResult(
                contract = GoogleAuthResult()
            ) { task ->
                try {
                    val account = task?.getResult(ApiException::class.java)
                    if (account == null) {
                        text = googleSignInFailed
                    } else {
                        scope.launch {
                            account.idToken?.let {
                                googleSignInViewModel.uploadUserGoogleTokenId(
                                    it
                                )
                            }
                        }
                    }
                } catch (e: ApiException) {
                    text = e.localizedMessage
                }
            }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            GoogleSignInButton(
                text = stringResource(
                    id = R.string.sign_in_with_google
                )
            ) {
                authResultLauncher.launch(signInRequestCode)
            }
        }
        AuthView(
            errorText = text,
            onClick = {
                text = null
                authResultLauncher.launch(signInRequestCode)
            }
        )
    }
}

@Composable
fun AuthView(
    errorText: String?,
    onClick: () -> Unit
) {
    var isLoading by remember { mutableStateOf(false) }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.ic_townhall_colored
                ),
                contentDescription = Constants.FitLabIcon,
                modifier = Modifier.padding(bottom = 60.dp)
            )
            Text(
                text = stringResource(id = R.string.sign_in),
                fontWeight = FontWeight(500),
                fontSize = 18.sp,
                lineHeight = 27.sp
            )
            GoogleSignInButton(
                text = stringResource(id = R.string.sign_in_with_google),
                icon = painterResource(id = R.drawable.ic_google_logo),
                loadingText = stringResource(id = R.string.signing_in___),
                isLoading = isLoading,
                modifier = Modifier.padding(25.dp),
                onClick = {
                    isLoading = true
                    onClick()
                }
            )
            Row {
                Text(
                    text = stringResource(R.string.dont_have_account_text),
                    modifier = Modifier.padding(end = 4.dp)
                )
                Text(
                    text = stringResource(R.string.register),
                    color = CustomBluePrimary
                )
            }

            errorText?.let {
                isLoading = false
            }
        }
    }
}

@Preview
@Composable
fun SignInScreenPreview() {
    AuthView(errorText = "null") {}
}