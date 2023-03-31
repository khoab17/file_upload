package com.sharjahuniversity.type2dm_poc.data.remote

import android.content.SharedPreferences
import com.sharjahuniversity.type2dm_poc.utils.Constants
import com.sharjahuniversity.type2dm_poc.utils.Constants.HeaderCookie
import com.sharjahuniversity.type2dm_poc.utils.Constants.HeaderSetCookie
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class ReceivedCookiesInterceptor(private val sharedPreferences: SharedPreferences) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse: Response = chain.proceed(chain.request())
        if (originalResponse.headers(HeaderSetCookie).isNotEmpty()) {
            val cookies: HashSet<String> = HashSet()
            for (header in originalResponse.headers(HeaderSetCookie)) {
                cookies.add(header)
            }
            sharedPreferences.edit().apply {
                putStringSet(Constants.Cookie, cookies)
                apply()
                commit()
            }
        }
        return originalResponse
    }
}

class AddCookiesInterceptor(private val sharedPreferences: SharedPreferences) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        val cookies: HashSet<String> =
            sharedPreferences.getStringSet(Constants.Cookie, HashSet<String>()) as HashSet<String>
        for (cookie in cookies) {
            builder.addHeader(HeaderCookie, cookie)
        }
        return chain.proceed(builder.build())
    }
}



