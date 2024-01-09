package com.hado.jetpackkotlinfundamentals.restaurants.domain

import com.hado.jetpackkotlinfundamentals.restaurants.data.RestaurantsRepository

class GetSortedRestaurantsUseCase {
    private val repository: RestaurantsRepository = RestaurantsRepository()

    suspend operator fun invoke(): List<Restaurant> {
        return repository.getRestaurants().sortedBy { it.title }
    }
}