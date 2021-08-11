package com.example.myapplication.network

import com.example.myapplication.home.listfragment.model.UserListResponseModel
import retrofit2.http.*

interface WebServices {


    /**
     * Coroutines API Call
     * Use Suspend KEY Word
     * **/
    @GET("users")
    suspend fun apiUserList(): UserListResponseModel

}