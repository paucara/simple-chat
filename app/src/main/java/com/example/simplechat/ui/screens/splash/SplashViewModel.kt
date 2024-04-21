package com.example.simplechat.ui.screens.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplechat.data.service.AccountService
import com.example.simplechat.ui.screens.main.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val accountService: AccountService
) : ViewModel() {
    fun onAppStart(openAndPopUp: (String, String) -> Unit) {
        if (accountService.hasUser) openAndPopUp(Screens.Chat.route, Screens.Splash.route)
        else onAuthenticate(openAndPopUp)
    }

    private fun onAuthenticate(openAndPopUp: (String, String) -> Unit) {
        viewModelScope.launch {
            try {
                accountService.authenticate()
                openAndPopUp(Screens.Chat.route, Screens.Splash.route)
            } catch (e: Exception) {
                Log.e("Error", "Description: $e")
            }
        }
    }
}