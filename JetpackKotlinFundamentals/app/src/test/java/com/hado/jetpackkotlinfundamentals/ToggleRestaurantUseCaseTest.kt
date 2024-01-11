package com.hado.jetpackkotlinfundamentals

import com.hado.jetpackkotlinfundamentals.fakes.FakeApiService
import com.hado.jetpackkotlinfundamentals.fakes.FakeRoomDao
import com.hado.jetpackkotlinfundamentals.restaurants.DummyContent
import com.hado.jetpackkotlinfundamentals.restaurants.data.RestaurantsRepository
import com.hado.jetpackkotlinfundamentals.restaurants.domain.GetSortedRestaurantsUseCase
import com.hado.jetpackkotlinfundamentals.restaurants.domain.ToggleRestaurantUseCase
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ToggleRestaurantUseCaseTest {
    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)

    @Test
    fun toggleRestaurant_IsUpdatingFavoriteField() = scope.runTest {
        // Setup useCase
        val restaurantsRepository = RestaurantsRepository(
            FakeApiService(),
            FakeRoomDao(),
            dispatcher
        )
        val getSortedRestaurantsUseCase = GetSortedRestaurantsUseCase(restaurantsRepository)
        val useCase = ToggleRestaurantUseCase(
            restaurantsRepository,
            getSortedRestaurantsUseCase
        )

        // Preload data
        restaurantsRepository.loadRestaurants()
        advanceUntilIdle()

        // Execute useCase
        val restaurants = DummyContent.getDomainRestaurants()
        val targetItem = restaurants[0]
        val isFavorite = targetItem.isFavorite
        val updatedRestaurants = useCase(
            targetItem.id,
            isFavorite
        )
        advanceUntilIdle()
        // Assertion
        restaurants[0] = targetItem.copy(isFavorite = !isFavorite)
        assert(updatedRestaurants == restaurants)
    }
}