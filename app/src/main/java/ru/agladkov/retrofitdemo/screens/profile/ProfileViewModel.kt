package ru.agladkov.retrofitdemo.screens.profile

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import ru.agladkov.retrofitdemo.network.DotaApi
import ru.agladkov.retrofitdemo.network.QuestGoApi
import ru.agladkov.retrofitdemo.screens.profile.models.RetrofitCreateElementRequest
import java.io.ByteArrayOutputStream
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val questGoApi: QuestGoApi) : ViewModel() {

    fun sendPostRequest() {
        viewModelScope.launch {
            try {
                questGoApi.sendElementRequest(
                    RetrofitCreateElementRequest(
                        accessToken = "",
                        questId = "",
                        pageId = "",
                        type = "1",
                        content = "Tomsk Info",
                        componentType = 0
                    )
                )
            } catch (e: Exception) {
                Log.e("TAG", "Exception during request -> ${e.localizedMessage}")
            }
        }
    }

    fun sendFileRequest(image: Bitmap) {
        val stream = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 80, stream)
        val byteArray = stream.toByteArray()
        val body = MultipartBody.Part.createFormData(
            "photo[content]", "photo",
            byteArray.toRequestBody("image/*".toMediaTypeOrNull(), 0, byteArray.size)
        )

        viewModelScope.launch(Dispatchers.IO) {
            try {
                questGoApi.sendFile(body)
            } catch (e: java.lang.Exception) {

            }
        }
    }
}