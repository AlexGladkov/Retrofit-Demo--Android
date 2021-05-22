package ru.agladkov.retrofitdemo.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Retrofit

@HiltViewModel
class LoginViewModel(val retrofit: Retrofit): ViewModel() {

    fun fetchHeroes() {
        viewModelScope.launch {

        }
    }
}