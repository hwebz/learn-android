package com.hado.jetpackkotlinfundamentals

import com.hado.jetpackkotlinfundamentals.fakes.FakeApiService
import com.hado.jetpackkotlinfundamentals.fakes.FakeRoomDao
import com.hado.jetpackkotlinfundamentals.restaurants.DummyContent
import com.hado.jetpackkotlinfundamentals.restaurants.data.RestaurantsRepository
import com.hado.jetpackkotlinfundamentals.restaurants.domain.GetInitialRestaurantsUseCase
import com.hado.jetpackkotlinfundamentals.restaurants.domain.GetSortedRestaurantsUseCase
import com.hado.jetpackkotlinfundamentals.restaurants.domain.ToggleRestaurantUseCase
import com.hado.jetpackkotlinfundamentals.restaurants.presentation.list.RestaurantsScreenState
import com.hado.jetpackkotlinfundamentals.restaurants.presentation.list.RestaurantsViewModel
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

class RestaurantsViewModelTest {
    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)

    private val errorMessage = "Something wrong!"
    private fun getViewModel(): RestaurantsViewModel {
        val restaurantsRepository = RestaurantsRepository(FakeApiService(), FakeRoomDao(), dispatcher)
        val getSortedRestaurantsUseCase = GetSortedRestaurantsUseCase(restaurantsRepository)
        val getInitialRestaurantsUseCase = GetInitialRestaurantsUseCase(restaurantsRepository, getSortedRestaurantsUseCase)
        val toggleRestaurantUseCase = ToggleRestaurantUseCase(restaurantsRepository, getSortedRestaurantsUseCase)

        return RestaurantsViewModel(
            getInitialRestaurantsUseCase,
            toggleRestaurantUseCase,
            dispatcher
        )
    }

    private fun getViewModelWithError(): RestaurantsViewModel {
        val restaurantsRepository = RestaurantsRepository(FakeApiService(errorMessage), FakeRoomDao(), dispatcher)
        val getSortedRestaurantsUseCase = GetSortedRestaurantsUseCase(restaurantsRepository)
        val getInitialRestaurantsUseCase = GetInitialRestaurantsUseCase(restaurantsRepository, getSortedRestaurantsUseCase)
        val toggleRestaurantUseCase = ToggleRestaurantUseCase(restaurantsRepository, getSortedRestaurantsUseCase)
        return RestaurantsViewModel(
            getInitialRestaurantsUseCase,
            toggleRestaurantUseCase,
            dispatcher
        )
    }

    @Test
    fun initialState_isProduced() = scope.runTest {
        val viewModel = getViewModel()
        val initialState = viewModel.state.value

        assert(initialState == RestaurantsScreenState(
            restaurants = emptyList(),
            isLoading = true,
            error = null
        ))
    }

    @Test
    fun stateWithContent_isProduced() = scope.runTest {
        val testVM = getViewModel()
        // because of async task inside VM, we need to call this one to
        // make sure it finished execution before assert
        advanceUntilIdle()
        val currentState = testVM.state.value

        assert(
            currentState == RestaurantsScreenState(
                restaurants = DummyContent.getDomainRestaurants(),
                isLoading = false,
                error = null
            )
        )
    }

    @Test
    fun stateWithError_isProduced() = scope.runTest {
        val testVM = getViewModelWithError()
        advanceUntilIdle()
        val currentState = testVM.state.value

        assert(
            currentState == RestaurantsScreenState(
                restaurants = emptyList(),
                isLoading = false,
                error = errorMessage
            )
        )
    }
}