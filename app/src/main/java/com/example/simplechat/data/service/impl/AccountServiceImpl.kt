package com.example.simplechat.data.service.impl

import com.example.simplechat.data.service.AccountService
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class AccountServiceImpl @Inject constructor(
    private val auth : FirebaseAuth
): AccountService {
    override val hasUser: Boolean
        get() = auth.currentUser != null

    override suspend fun authenticate() {
        auth.signInAnonymously()
    }
}