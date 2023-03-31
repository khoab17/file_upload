package com.sharjahuniversity.type2dm_poc.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.icu.util.Calendar
import android.net.ConnectivityManager
import android.os.Build
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.drawable.toDrawable
import androidx.room.TypeConverter
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sharjahuniversity.type2dm_poc.data.model.GoogleUserModel
import com.sharjahuniversity.type2dm_poc.data.model.IconDetails
import com.sharjahuniversity.type2dm_poc.utils.Constants.HasNewGoalsSet
import com.sharjahuniversity.type2dm_poc.utils.Constants.HasNewSuggestion
import com.sharjahuniversity.type2dm_poc.utils.Constants.HasNewSurvey
import com.sharjahuniversity.type2dm_poc.utils.Constants.IsProfileDataInputComplete
import com.sharjahuniversity.type2dm_poc.utils.Constants.IsUserActive
import com.sharjahuniversity.type2dm_poc.utils.Constants.SharedPreferencesFileName
import com.sharjahuniversity.type2dm_poc.utils.Constants.UserId
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object Utils {
    fun setIsProfileDataInputComplete(mContext: Context, isComplete: Boolean) {
        val editor =
            mContext.getSharedPreferences(SharedPreferencesFileName, Context.MODE_PRIVATE).edit()
        editor.putBoolean(IsProfileDataInputComplete, isComplete)
        editor.apply()
        editor.commit()
    }

    fun getIsProfileDataInputComplete(mContext: Context): Boolean {
        return mContext.getSharedPreferences(SharedPreferencesFileName, Context.MODE_PRIVATE)
            .getBoolean(IsProfileDataInputComplete, false)
    }

    fun isInternetConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm.activeNetwork != null && cm.getNetworkCapabilities(cm.activeNetwork) != null
        } else {
            cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnectedOrConnecting
        }
    }

    fun setUserId(mContext: Context, id: String) {
        val editor =
            mContext.getSharedPreferences(SharedPreferencesFileName, Context.MODE_PRIVATE).edit()
        editor.putString(UserId, id)
        editor.apply()
        editor.commit()
    }

    fun getUserId(mContext: Context): String? {
        return mContext.getSharedPreferences(SharedPreferencesFileName, Context.MODE_PRIVATE)
            .getString(UserId, null)
    }

    fun setIsUserStatusActive(mContext: Context, isActive: Boolean) {
        val editor =
            mContext.getSharedPreferences(SharedPreferencesFileName, Context.MODE_PRIVATE).edit()
        editor.putBoolean(IsUserActive, isActive)
        editor.apply()
        editor.commit()
    }

    fun getIsUserStatusActive(mContext: Context): Boolean {
        return mContext.getSharedPreferences(SharedPreferencesFileName, Context.MODE_PRIVATE)
            .getBoolean(IsUserActive, false)
    }

    fun setHasNewSuggestion(mContext: Context, hasNewSuggestion: Boolean) {
        val editor =
            mContext.getSharedPreferences(SharedPreferencesFileName, Context.MODE_PRIVATE).edit()
        editor.putBoolean(HasNewSuggestion, hasNewSuggestion)
        editor.apply()
        editor.commit()
    }

    fun getHasNewSuggestion(mContext: Context): Boolean {
        return mContext.getSharedPreferences(SharedPreferencesFileName, Context.MODE_PRIVATE)
            .getBoolean(HasNewSuggestion, false)
    }

    fun setHasNewSurvey(mContext: Context, hasNewSurvey: Boolean) {
        val editor =
            mContext.getSharedPreferences(SharedPreferencesFileName, Context.MODE_PRIVATE).edit()
        editor.putBoolean(HasNewSurvey, hasNewSurvey)
        editor.apply()
        editor.commit()
    }

    fun getHasNewSurvey(mContext: Context): Boolean {
        return mContext.getSharedPreferences(SharedPreferencesFileName, Context.MODE_PRIVATE)
            .getBoolean(HasNewSurvey, false)
    }

    fun setHasNewGoalsSet(mContext: Context, hasNewGoalsSet: Boolean) {
        val editor =
            mContext.getSharedPreferences(SharedPreferencesFileName, Context.MODE_PRIVATE).edit()
        editor.putBoolean(HasNewGoalsSet, hasNewGoalsSet)
        editor.apply()
        editor.commit()
    }

    fun getHasNewGoalsSet(mContext: Context): Boolean {
        return mContext.getSharedPreferences(SharedPreferencesFileName, Context.MODE_PRIVATE)
            .getBoolean(HasNewGoalsSet, false)
    }

    fun isSignedIn(mContext: Context): Boolean {
        return GoogleSignIn.getLastSignedInAccount(mContext) != null
    }

    fun getUserDetails(mContext: Context): GoogleUserModel {
        val account = GoogleSignIn.getLastSignedInAccount(mContext)
        return GoogleUserModel(
            account?.displayName!!,
            account.email!!,
            account.idToken!!
        )
    }

    fun signOut(mContext: Context) {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()

        val googleSignInClient = GoogleSignIn.getClient(mContext, gso)
        googleSignInClient.signOut()
    }

    @Composable
    fun animateFloatValue(value: Float): Float {
        var progress by remember {
            mutableStateOf(0f)
        }
        val animatedProgress = animateFloatAsState(
            targetValue = progress,
            animationSpec = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            )
        ).value
        LaunchedEffect(value) {
            progress = value
        }
        return animatedProgress
    }

    @Composable
    fun loadSVGImage(icon: IconDetails): AsyncImagePainter {
        return rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .decoderFactory(SvgDecoder.Factory())
                .data(icon.url)
                .placeholder(icon.iconId)
                .error(icon.iconId)
                .diskCachePolicy(CachePolicy.ENABLED)
                .networkCachePolicy(CachePolicy.ENABLED)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .size(80) // Set the target size to load the image at.
                .build()
        )
    }

    @Composable
    fun loadIcon(icon: IconDetails): MutableState<Bitmap?> {
        val bitmapState: MutableState<Bitmap?> = mutableStateOf(null)
        if (icon.url != "" && icon.url != null)
            Glide.with(LocalContext.current)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .load(icon.url)
                .error(icon.iconId)
                .fallback(icon.iconId)
                .placeholder(icon.iconId)
                .into(object : CustomTarget<Bitmap>(64, 64) {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        bitmapState.value = resource
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {

                    }
                })
                .onLoadFailed(icon.iconId.toDrawable())
        else Glide.with(LocalContext.current)
            .asBitmap()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .load(icon.iconId)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap>?
                ) {
                    bitmapState.value = resource
                }

                override fun onLoadCleared(placeholder: Drawable?) {

                }
            })
        return bitmapState
    }

    fun getCurrentDate(): String =
        SimpleDateFormat(Constants.DATE_FORMAT, Locale.ENGLISH).format(Date())

    fun get7DaysAgoDate(): String =
        SimpleDateFormat(Constants.DATE_FORMAT, Locale.ENGLISH).format(
            Calendar.getInstance().add(Calendar.DATE, -7)
        )

    fun convertStringDateToUploadFormat(date: String): String =
        SimpleDateFormat(Constants.DATE_FORMAT_TO_UPLOAD, Locale.ENGLISH).format(
            SimpleDateFormat(Constants.DATE_FORMAT, Locale.ENGLISH).parse(date)
        )

    fun getCurrentDateTime(): String =
        SimpleDateFormat(Constants.DATE_TIME_FORMAT, Locale.ENGLISH).format(Date())

    fun serverDateTimeToLocalDBDateTime(dateTime: String): String {
        return SimpleDateFormat(Constants.DATE_TIME_FORMAT, Locale.ENGLISH).format(
            SimpleDateFormat(Constants.DATE_TIME_FORMAT_SERVER, Locale.ENGLISH).parse(dateTime)
        )
    }

    fun serverDateTimeToLocalDBDate(dateTime: String): String {
        return SimpleDateFormat(Constants.DATE_FORMAT, Locale.ENGLISH).format(
            SimpleDateFormat(Constants.DATE_TIME_FORMAT_SERVER, Locale.ENGLISH).parse(dateTime)
        )
    }

    fun getTimeDifferenceInSMHDM(dateTime: String): String {
        val dateTimeTill =
            SimpleDateFormat(Constants.DATE_TIME_FORMAT, Locale.ENGLISH).parse(dateTime)
        val currentDateTime = Date()
        val difference = currentDateTime.time - dateTimeTill.time
        return if (TimeUnit.MILLISECONDS.toDays(difference) >= 1) "${
            TimeUnit.MILLISECONDS.toDays(
                difference
            )
        } days ago"
        else if (TimeUnit.MILLISECONDS.toHours(difference) >= 1) "${
            TimeUnit.MILLISECONDS.toHours(
                difference
            )
        } hours ago"
        else if (TimeUnit.MILLISECONDS.toMinutes(difference) >= 1) "${
            TimeUnit.MILLISECONDS.toMinutes(
                difference
            )
        } minutes ago"
        else "${TimeUnit.MILLISECONDS.toSeconds(difference)} seconds ago"
    }

    @TypeConverter
    fun stringToStringList(listOfString: String): List<String> {
        return Gson().fromJson(listOfString, object : TypeToken<List<String>>() {}.type)
    }

    @TypeConverter
    fun stringListToString(listOfString: List<String>): String {
        return Gson().toJson(listOfString)
    }

    fun getInitialIntervalTillDailyResetTime(dueDate: Calendar): Long {
        val currentDate = Calendar.getInstance()
        if (dueDate.before(currentDate)) {
            dueDate.add(Calendar.HOUR_OF_DAY, 24)
        }

        return dueDate.timeInMillis - currentDate.timeInMillis
    }

    fun convertFeetInchToInch(feetInch: String): Float {
        val splatted = feetInch.split("\'")
        return splatted[0].toFloat() * 12 + splatted[1].split("\"")[0].toFloat()
    }

    fun convertInchToCM(inch: Float): Float {
        return inch * 2.54f
    }

    fun convertTo2DigitNumber(number: Int): String = String.format("%02d", number)
    fun convertTo2DigitNumber(number: String): String = String.format("%s", number)
    fun convertToLocalizedEnglish(number: Int): String {
        return String.format(Locale.ENGLISH, "%02d", number)
    }

    fun getValidatedFloatNumber(text: String): String {
        // Start by filtering out unwanted characters like commas and multiple decimals
        val filteredChars = text.filterIndexed { index, c ->
            c in "0123456789" ||                      // Take all digits
                    (c == '.' && text.indexOf('.') == index)  // Take only the first decimal
        }
        // Now we need to remove extra digits from the input
        return if (filteredChars.contains('.')) {
            val beforeDecimal = filteredChars.substringBefore('.')
            val afterDecimal = filteredChars.substringAfter('.')
            beforeDecimal + "." + afterDecimal.take(2)    // If decimal is present, take first 3 digits before decimal and first 2 digits after decimal
        } else {
            filteredChars.take(3)                     // If there is no decimal, just take the first 3 digits
        }
    }
}