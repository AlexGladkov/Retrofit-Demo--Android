package ru.agladkov.retrofitdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import dagger.hilt.android.AndroidEntryPoint
import ru.agladkov.retrofitdemo.navigation.BaseNavigation
import ru.agladkov.retrofitdemo.screens.login.LoginFragment
import ru.agladkov.retrofitdemo.screens.profile.ProfileFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), BaseNavigation {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (getSharedPreferences(getString(R.string.app_name), 0).getString("API_KEY", "").isNullOrBlank()) {
            openScreen(LoginFragment()) } else {
            openScreen(ProfileFragment())
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val callback = object : VKAuthCallback {
            override fun onLogin(token: VKAccessToken) {
                getSharedPreferences(getString(R.string.app_name), 0)
                    ?.edit()?.putString("API_KEY", token.accessToken)?.apply()
                openScreen(ProfileFragment())
            }

            override fun onLoginFailed(errorCode: Int) {
                Toast.makeText(
                    applicationContext,
                    "Error while login $errorCode",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun openScreen(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}