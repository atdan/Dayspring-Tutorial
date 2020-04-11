package com.atuma.dayspringtutorials.util

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import com.atuma.dayspringtutorials.R

public class Utils {

    fun animateEnterRight(activity: Activity) {
        activity.overridePendingTransition(R.anim.enter_b, R.anim.exit_a)
    }

    fun animateEnterLeft(activity: Activity) {
        activity.overridePendingTransition(R.anim.enter_a, R.anim.exit_b)
    }

    fun animateFadein(activity: Activity) {
        activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    public fun drawableToBitmap(drawable: Drawable): Bitmap {
        if (drawable is BitmapDrawable) {
            if (drawable.bitmap != null) {
                return drawable.bitmap
            }
        }

        val bitmap: Bitmap = if (drawable.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0) {
            Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888) // Single color bitmap will be created of 1x1 pixel
        } else {
            Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        }

        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }
    fun getDarkColor(color: Int): Int {
        val red = Color.red(color)
        val green = Color.green(color)
        val blue = Color.blue(color)
        return Color.rgb(
            (red * 0.8).toInt(),
            (green * 0.8).toInt(),
            (blue * 0.8).toInt()
        )
    }

    fun getLightColor(color: Int): Int {
        val red = Color.red(color)
        val green = Color.green(color)
        val blue = Color.blue(color)
        return Color.argb((255 * 0.5).toInt(), red, green, blue)
    }
}