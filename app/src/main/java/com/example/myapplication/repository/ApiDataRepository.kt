package com.example.myapplication.repository

import com.example.myapplication.network.AuthState
import com.example.myapplication.network.WebServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ApiDataRepository(private val apiLists: WebServices) {

    fun apiUserList() = flow {
        emit(AuthState.loading())

        apiLists.apiUserList().let {

            emit(
                if (it.data.isNotEmpty()) {
                    AuthState.authenticated(it.data)
                } else {
                    AuthState.authenticationFailed("No User List Found")
                }
            )


        }


    }.catch {
        emit(AuthState.authenticationFailed("Some thing went wrong"))
    }.flowOn(Dispatchers.IO)
}