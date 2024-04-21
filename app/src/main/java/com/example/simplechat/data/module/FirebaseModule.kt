package com.example.simplechat.data.module

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule{
    @Provides
    fun auth(): FirebaseAuth = Firebase.auth

    @Provides
    fun database() : FirebaseDatabase = Firebase.database
}