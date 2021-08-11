package com.example.myapplication.home.listfragment.model

import com.google.gson.annotations.SerializedName

data class UserListResponseModel(
    @SerializedName("page") var page: String,
    @SerializedName("per_page") var per_page: String,
    @SerializedName("total") var total: String,
    @SerializedName("total_pages") var total_pages: String,
    @SerializedName("data") var data: List<UserModel> = emptyList()
    )