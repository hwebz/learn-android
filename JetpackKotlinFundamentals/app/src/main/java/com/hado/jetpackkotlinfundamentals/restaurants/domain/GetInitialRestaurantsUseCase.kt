package com.hado.jetpackkotlinfundamentals.restaurants.domain

import com.hado.jetpackkotlinfundamentals.restaurants.data.RestaurantsRepository

class GetInitialRestaurantsUseCase {
    private val repository: RestaurantsRepository = RestaurantsRepository()
    private val getSortedRestaurantsUseCase = GetSortedRestaurantsUseCase()

    suspend operator fun invoke(): List<Restaurant> {
//        return repository.loadRestaurants().sortedBy { it.title }
        repository.loadRestaurants()
        return getSortedRestaurantsUseCase()
    }
}