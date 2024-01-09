package com.hado.jetpackkotlinfundamentals

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RestaurantsApiService {
    @GET("restaurants.json")
    // fun getRestaurants(): Call<List<Restaurant>>
    // suspend supports coroutines that not blocking main thread UI
    suspend fun getRestaurants(): List<RemoteRestaurant>

//    @POST("user/edit")
//    fun updateUser(@Field("first_name") firstName: String): Call<User>

    @GET("restaurants.json?orderBy=\"r_id\"")
    suspend fun getRestaurant(@Query("equalTo") id: Int): Map<String, RemoteRestaurant>
}