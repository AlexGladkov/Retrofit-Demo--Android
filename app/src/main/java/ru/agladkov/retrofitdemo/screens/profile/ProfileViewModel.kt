package ru.agladkov.retrofitdemo.screens.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.agladkov.retrofitdemo.network.DotaApi
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val dotaApi: DotaApi): ViewModel() {

    fun fetchHeroes() {
        viewModelScope.launch {
            val heroes = dotaApi.fetchHeroes()
            Log.e("TAG", "Heroes -> $heroes")
        }
    }
}