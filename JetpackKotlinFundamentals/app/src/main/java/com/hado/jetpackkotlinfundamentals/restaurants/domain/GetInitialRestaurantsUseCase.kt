package com.hado.jetpackkotlinfundamentals.restaurants.domain

import com.hado.jetpackkotlinfundamentals.restaurants.data.RestaurantsRepository
import javax.inject.Inject

class GetInitialRestaurantsUseCase @Inject constructor(
    private val repository: RestaurantsRepository,
    private val getSortedRestaurantsUseCase: GetSortedRestaurantsUseCase
) {
//    private val repository: RestaurantsRepository = RestaurantsRepository()
//    private val getSortedRestaurantsUseCase = GetSortedRestaurantsUseCase()

    suspend operator fun invoke(): List<Restaurant> {
//        return repository.loadRestaurants().sortedBy { it.title }
        repository.loadRestaurants()
        return getSortedRestaurantsUseCase()
    }
}