package ru.agladkov.retrofitdemo.screens.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.auth.VKScope
import dagger.hilt.android.AndroidEntryPoint
import ru.agladkov.retrofitdemo.R
import ru.agladkov.retrofitdemo.navigation.BaseNavigation
import ru.agladkov.retrofitdemo.screens.profile.ProfileFragment

@AndroidEntryPoint
class LoginFragment: Fragment(R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<AppCompatButton>(R.id.loginView)
            .setOnClickListener {
                (activity as? BaseNavigation)?.openScreen(ProfileFragment())
            }
    }
}