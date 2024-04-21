package com.example.simplechat.utils

private const val MIN_PASS_LENGTH = 6


fun String.isValidName(): Boolean {
    return this.isNotBlank() && this.length >= MIN_PASS_LENGTH
}
