package com.shain.messenger.utils

import android.content.Context

/**
 * Created by Shain Singh on 2/4/19.
 */
object AndroidUtils {

    private var density = 1f

    fun dp(value: Float, context: Context): Int {
        if (density == 1f) {
            checkDisplaySize(context)
        }
        return if (value == 0f) {
            0
        } else Math.ceil((density * value).toDouble()).toInt()
    }


    private fun checkDisplaySize(context: Context) {
        try {
            density = context.resources.displayMetrics.density
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}