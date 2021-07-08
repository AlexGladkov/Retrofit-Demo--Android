package ru.agladkov.retrofitdemo.screens.profile

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.vk.api.sdk.requests.VKRequest
import dagger.hilt.android.AndroidEntryPoint
import ru.agladkov.retrofitdemo.R

@AndroidEntryPoint
class ProfileFragment: Fragment(R.layout.fragment_profile) {

    private val viewModel: ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<AppCompatButton>(R.id.postRequestButtonView).setOnClickListener { sendPostRequest() }
        view.findViewById<AppCompatButton>(R.id.sendFileButtonView).setOnClickListener { sendFileRequest() }
    }

    private fun sendPostRequest() {
        
    }

    private fun sendFileRequest() {

    }
}