package com.hado.jetpackkotlinfundamentals

class ToggleRestaurantUseCase {
    private val repository: RestaurantsRepository = RestaurantsRepository()
    private val getSortedRestaurantsUseCase = GetSortedRestaurantsUseCase()

    suspend operator fun invoke(id: Int, oldValue: Boolean): List<Restaurant> {
        val newFav = oldValue.not()
        repository.toggleFavoriteRestaurant(id, newFav)
        // Fix the issue of restaurantsDao.getAll() inside RestaurantsRepository.toggleFavoriteRestaurant
        // is a unsorted restaurants list, it gonna flickers
        // This one gonna make API call and refresh cache, but we don't want
        // to make any API call + refresh cache with only UI change
//        return GetRestaurantsUseCase().invoke()
        // We use this one to fetch restaurants from cache (Room db)
        return getSortedRestaurantsUseCase()
    }
}