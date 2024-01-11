package com.hado.jetpackkotlinfundamentals.fakes

import com.hado.jetpackkotlinfundamentals.restaurants.DummyContent
import com.hado.jetpackkotlinfundamentals.restaurants.data.remote.RemoteRestaurant
import com.hado.jetpackkotlinfundamentals.restaurants.data.remote.RestaurantsApiService
import kotlinx.coroutines.delay

class FakeApiService(
    private val errorMessage: String = ""
): RestaurantsApiService {
    override suspend fun getRestaurants(): List<RemoteRestaurant> {
        delay(1000)
        if (errorMessage.isNotEmpty()) {
            throw Exception(errorMessage)
        }
        return DummyContent.getRemoteRestaurants()
    }

    override suspend fun getRestaurant(id: Int): Map<String, RemoteRestaurant> {
        TODO("Not yet implemented")
    }
}