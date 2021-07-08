package ru.agladkov.retrofitdemo.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import ru.agladkov.retrofitdemo.network.DotaApi
import ru.agladkov.retrofitdemo.network.DotaRetrofitClient
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val dotaApi: DotaApi): ViewModel() {


}