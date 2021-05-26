package com.example.mvvm.data.api

import com.example.mvvm.data.models.SearchResponse
import com.example.mvvm.data.models.User
//import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {

    @GET("users")
    suspend fun getUsers() : Response<List<User>>
    // We don't know what would it return, so for now we're returning Response<JsonObject>. Use the correct import.
    // After we know ehat this returns, we change it's return type.

    @GET("search/users")
    suspend fun searchUser(@Query("q")query: String) : Response<SearchResponse>
 // Here, q is the query param

}
