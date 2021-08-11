package com.example.myapplication.home.listfragment.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModel (
    @SerializedName("id") var id: Int,
    @SerializedName("email") var email: String,
    @SerializedName("first_name") var first_name: String,
    @SerializedName("last_name") var last_name: String,
    @SerializedName("avatar") var avatar: String
    ) : Parcelable
