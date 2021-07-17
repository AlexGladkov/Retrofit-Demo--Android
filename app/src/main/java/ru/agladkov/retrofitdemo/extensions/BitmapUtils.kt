package ru.agladkov.retrofitdemo.extensions

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.FileNotFoundException

object BitmapUtils {

    private const val IMG_MAX_SIDE_SIZE = 2000

    fun decodeBitmap(uri: Uri, activity: Activity): Bitmap? {
        return try {
            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            BitmapFactory.decodeStream(activity.contentResolver.openInputStream(uri), null, options)
            var scale = 1
            while (options.outWidth / scale / 2 >= IMG_MAX_SIDE_SIZE && options.outHeight / scale / 2 >= IMG_MAX_SIDE_SIZE) scale *= 2

            val scaleOptions = BitmapFactory.Options()
            scaleOptions.inSampleSize = scale
            BitmapFactory.decodeStream(activity.contentResolver.openInputStream(uri), null, scaleOptions)
        } catch (e: FileNotFoundException) {
            null
        }
    }
}