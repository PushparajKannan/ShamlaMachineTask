package com.example.myapplication.network

sealed class AuthState<T> {
    class Loading<T> : AuthState<T>()
    data class Authenticated<T>(val data: T) : AuthState<T>()
    data class AuthenticationFailed<T>(val message: String) : AuthState<T>()

    companion object {
        fun <T> loading() = Loading<T>()
        fun <T> authenticated(data: T) = Authenticated(data)
        fun <T> authenticationFailed(message: String) = AuthenticationFailed<T>(message)
    }
}