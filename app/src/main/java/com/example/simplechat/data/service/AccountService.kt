package com.example.simplechat.data.service

interface AccountService {
    val hasUser: Boolean

    suspend fun authenticate()
}
