package ru.agladkov.retrofitdemo.extensions

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import java.io.ByteArrayOutputStream

fun Intent.createBitmapFromResult(activity: Activity): Bitmap? {
    val intentBundle = this.extras
    val intentUri = this.data
    var bitmap: Bitmap? = null
    if (intentBundle != null) {
        bitmap = (intentBundle.get("data") as? Bitmap)?.apply {
            compress(Bitmap.CompressFormat.JPEG, 75, ByteArrayOutputStream())
        }
    }

    if (bitmap == null && intentUri != null) {
        intentUri.let { bitmap = BitmapUtils.decodeBitmap(intentUri, activity) }
    }
    return bitmap
}