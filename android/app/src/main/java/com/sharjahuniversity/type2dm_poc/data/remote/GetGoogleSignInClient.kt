package com.sharjahuniversity.type2dm_poc.data.remote

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.sharjahuniversity.type2dm_poc.utils.Constants.ServerClientIDForGoogle

fun getGoogleSignIn(context: Context): GoogleSignInClient {
    val signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(ServerClientIDForGoogle)
        .requestEmail()
        .build()
    return GoogleSignIn.getClient(context, signInOptions)
}