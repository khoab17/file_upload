package com.sharjahuniversity.type2dm_poc.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.sharjahuniversity.type2dm_poc.R
import com.sharjahuniversity.type2dm_poc.ui.component.DialogWithOneButtonAndOneText
import com.sharjahuniversity.type2dm_poc.ui.theme.BackgroundColor
import com.sharjahuniversity.type2dm_poc.ui.theme.Type2DMPocTheme
import com.sharjahuniversity.type2dm_poc.ui.viewmodel.GoogleSignInViewModel
import com.sharjahuniversity.type2dm_poc.ui.viewmodel.PersonalDetailsInputScreenViewModel
import com.sharjahuniversity.type2dm_poc.utils.APIResponseStatus
import com.sharjahuniversity.type2dm_poc.utils.DataState
import com.sharjahuniversity.type2dm_poc.utils.UserStatus
import com.sharjahuniversity.type2dm_poc.utils.Utils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mGoogleSignInViewModel: GoogleSignInViewModel by viewModels()
    private val mPersonalDetailsInputScreenViewModel: PersonalDetailsInputScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            content()
        }
        Utils.getUserId(this)?.let {
            FirebaseMessaging.getInstance().token.addOnCompleteListener(
                OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        //Log.w("homeActivity", "Fetching FCM registration token failed", task.exception)
                        return@OnCompleteListener
                    }
                    // Get new FCM registration token
                    val token = task.result
                    mPersonalDetailsInputScreenViewModel.uploadUserFCMToken(
                        token,
                        it
                    )
                    Timber.d("FCM Token>>init: token: $token")
                })
        }

        initObserver()
    }

    @Composable
    private fun content() {
        Type2DMPocTheme {
            //A surface container using the 'background' color from the theme
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BackgroundColor),
                content = {
                    Log.d("PADDING", it.toString())
                    var startAnimation by remember {
                        mutableStateOf(false)
                    }
                    val alphaAnim = animateFloatAsState(
                        targetValue = if (startAnimation) 1f else 0f,
                        animationSpec = tween(durationMillis = 300)
                    )
                    LaunchedEffect(key1 = true) {
                        startAnimation = true
                        delay(800)
                    }
                    SplashScreen(alphaAnim.value)
                    if (alphaAnim.value == 1f) {
                        /*if (Utils.isSignedIn(this)) {
                            val account = getUserDetails(this)
                            if (Utils.getIsProfileDataInputComplete(this)) {
                                startActivity(Intent(this, HomeActivity::class.java))
                                finish()
                            } else {
                                PersonalDetailsInputScreen(
                                    this,
                                    account.displayName,
                                    account.email,
                                    mPersonalDetailsInputScreenViewModel
                                )
                            }
                        } else {
                            setContent { SignInScreen(mGoogleSignInViewModel) }
                        }*/
                        if (Utils.getIsProfileDataInputComplete(applicationContext)) {
                            if (Utils.getIsUserStatusActive(applicationContext)) {
                                startActivity(Intent(applicationContext, HomeActivity::class.java))
                                finish()
                            } else if (Utils.isInternetConnected(applicationContext))
                                Utils.getUserId(applicationContext)
                                    ?.let { id -> mPersonalDetailsInputScreenViewModel.fetchUser(id) }
                        } else {
                            setContent { SignInScreen(mGoogleSignInViewModel) }
                        }
                    }
                }
            )
        }
    }

    private fun initObserver() {
        mGoogleSignInViewModel.apply {
            authResponse.observe(this@MainActivity) {
                Timber.d("initObserver: google $it")
                when (it) {
                    is DataState.Success -> {
                        if (it.data.status == APIResponseStatus.Success.value) {
                            Utils.setUserId(
                                applicationContext,
                                it.data.authResponseData.authResponseDataUser.id
                            )
                            FirebaseMessaging.getInstance().token.addOnCompleteListener(
                                OnCompleteListener { task ->
                                    if (!task.isSuccessful) {
                                        //Log.d("homeActivity", "Fetching FCM registration token failed", task.exception)
                                        return@OnCompleteListener
                                    }

                                    // Get new FCM registration token
                                    val token = task.result
                                    mPersonalDetailsInputScreenViewModel.uploadUserFCMToken(
                                        token,
                                        it.data.authResponseData.authResponseDataUser.id
                                    )
                                    Timber.d("FCM Token>>authResponse: token: $token")
                                })
                            setContent {
                                PersonalDetailsInputScreen(
                                    mActivity = this@MainActivity,
                                    authResponseDataUser = it.data.authResponseData.authResponseDataUser,
                                    personalDetailsInputScreenViewModel = mPersonalDetailsInputScreenViewModel
                                )
                            }
                        }
                    }
                    else -> {
                        Utils.signOut(this@MainActivity)
                        setContent { SignInScreen(mGoogleSignInViewModel) }
                    }
                }

            }
        }
        mPersonalDetailsInputScreenViewModel.apply {
            userDataUploadResponse.observe(this@MainActivity) {
                when (it) {
                    is DataState.Success -> if (it.data.success) {
                        Timber.d("userDataUploadResponse: $it")
                        Utils.setIsProfileDataInputComplete(applicationContext, true)
                        if (it.data.userDataUploadResponseData.userDataUploadResponseDataUser.status == UserStatus.Active.value) {
                            Utils.setIsUserStatusActive(applicationContext, true)
                            startActivity(Intent(applicationContext, HomeActivity::class.java))
                            finish()
                        }
                    }
                    else -> {}
                }
            }
            fetchUserResponse.observe(this@MainActivity) {
                Timber.d("fetchUserResponse: $it")
                when (it) {
                    is DataState.Success -> {
                        if (it.data.success) {
                            when (it.data.fetchUserResponseData.fetchUserResponseDataUser.status) {
                                UserStatus.Active.value -> {
                                    Utils.setIsUserStatusActive(applicationContext, true)
                                    startActivity(
                                        Intent(
                                            applicationContext,
                                            HomeActivity::class.java
                                        )
                                    )
                                    finish()
                                }
                                UserStatus.Pending.value -> {
                                    Utils.setIsUserStatusActive(applicationContext, false)
                                    setContent {
                                        DialogWithOneButtonAndOneText(
                                            text = stringResource(id = R.string.wait_till_approve_dialog_text),
                                            buttonText = stringResource(id = R.string.ok),
                                            buttonClick = { this@MainActivity.finish() }) {
                                            this@MainActivity.finish()
                                        }
                                    }
                                }
                                UserStatus.Rejected.value -> {
                                    Utils.setIsUserStatusActive(applicationContext, false)
                                    setContent {
                                        DialogWithOneButtonAndOneText(
                                            text = getString(R.string.request_rejected_text),
                                            buttonText = getString(R.string.retry),
                                            buttonClick = {
                                                Utils.setIsProfileDataInputComplete(
                                                    applicationContext,
                                                    false
                                                )
                                                startActivity(
                                                    Intent(
                                                        applicationContext,
                                                        MainActivity::class.java
                                                    )
                                                )
                                                this@MainActivity.finish()
                                            }) {
                                            Utils.setIsProfileDataInputComplete(
                                                applicationContext,
                                                false
                                            )
                                            startActivity(
                                                Intent(
                                                    applicationContext,
                                                    MainActivity::class.java
                                                )
                                            )
                                            this@MainActivity.finish()
                                        }
                                    }
                                }
                                else -> {
                                    setContent { SignInScreen(mGoogleSignInViewModel) }
                                }
                            }
                        }
                    }
                    else -> {
                        setContent { SignInScreen(mGoogleSignInViewModel) }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    /*Type2DMPocTheme {
        SignInScreen(GoogleSignInViewModel())
    }*/
}