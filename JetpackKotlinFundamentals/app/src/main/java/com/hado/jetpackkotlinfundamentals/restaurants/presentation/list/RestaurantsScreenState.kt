package com.hado.jetpackkotlinfundamentals.restaurants.presentation.list

import com.hado.jetpackkotlinfundamentals.restaurants.domain.Restaurant

data class RestaurantsScreenState(
    val restaurants: List<Restaurant>,
    val isLoading: Boolean,
    val error: String? = null
)
