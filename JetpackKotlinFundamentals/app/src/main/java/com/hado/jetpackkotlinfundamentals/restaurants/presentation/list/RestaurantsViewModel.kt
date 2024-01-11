package com.hado.jetpackkotlinfundamentals.restaurants.presentation.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hado.jetpackkotlinfundamentals.restaurants.data.RestaurantsRepository
import com.hado.jetpackkotlinfundamentals.restaurants.domain.GetInitialRestaurantsUseCase
import com.hado.jetpackkotlinfundamentals.restaurants.domain.ToggleRestaurantUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

// The reason we need SavedStateHandle is when app running in background
// but system will kill our process to save the memory
// so all of our favorite restaurants are being cleared, we need to store it
//class RestaurantsViewModel(private val stateHandle: SavedStateHandle): ViewModel() {
@HiltViewModel
class RestaurantsViewModel @Inject constructor(
    private val getInitialRestaurantsUseCase: GetInitialRestaurantsUseCase,
    private val toggleRestaurantsUseCase: ToggleRestaurantUseCase
): ViewModel() {
//    private val respository = RestaurantsRepository()
//    private val getInitialRestaurantsUseCase = GetInitialRestaurantsUseCase()
//    private val toggleRestaurantsUseCase = ToggleRestaurantUseCase()
//    private var restInterface: RestaurantsApiService
//    private var restaurantsDao = RestaurantsDb.getDaoInstance(RestaurantsApplication.getAppContext())
//    val state = mutableStateOf(dummyRestaurants.restoreSelections())
    private val _state = mutableStateOf(
        RestaurantsScreenState(
            restaurants = listOf(),
            isLoading = true
        )
    )
    // prevent outside can't change the _state value
    val state: State<RestaurantsScreenState> get() = _state
//    private lateinit var restaurantsCall: Call<List<Restaurant>>
    val job = Job()
    private val scope = CoroutineScope(job + Dispatchers.IO)
    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
        _state.value = _state.value.copy(
            error = exception.message,
            isLoading = false
        )
    }

//    init {
//        val retrofit: Retrofit = Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl("https://kotlinrestaurants-1d5a6-default-rtdb.asia-southeast1.firebasedatabase.app/")
//            .build()
//        restInterface = retrofit.create(RestaurantsApiService::class.java)
//        // Instead of calling API from Composable, calling here to make sure
//        // it's only being called once when view model initialized
//        getRestaurants()
//    }
    init {
        getRestaurants()
    }

    private fun getRestaurants() {
        // throws NetworkOnMainThreadException
//        restInterface.getRestaurants().execute().body()?.let {restaurants ->
//            state.value = restaurants.restoreSelections()
//        }

        // .execute() -> synchronous
        // .enqueue() -> asynchronous -> recommended

//        restaurantsCall = restInterface.getRestaurants()
//        restaurantsCall.enqueue(
//            object: Callback<List<Restaurant>> {
//                override fun onResponse(
//                    call: Call<List<Restaurant>>,
//                    response: Response<List<Restaurant>>
//                ) {
//                    response.body()?.let { restaurants ->
//                        state.value = restaurants.restoreSelections()
//                    }
//                }
//
//                override fun onFailure(call: Call<List<Restaurant>>, t: Throwable) {
//                    t.printStackTrace()
//                }
//            }
//        )

        // Using coroutines instead of default Retrofit Call and Callback
        // Custom scope here you need to manually cancel the coroutines
//        scope.launch {
        // pre-defined scope will automatically take care of cancelling all of the coroutines
        // launched within its parent when instance has been cleared or destroyed
//        viewModelScope.launch(Dispatchers.IO + errorHandler) {
            viewModelScope.launch(errorHandler) {
            // errorHandler above handling exception for coroutines instead of nested try catch below
//            try {
                // val restaurants = restInterface.getRestaurants()
//                val restaurants = getAllRestaurants()
//                withContext(Dispatchers.Main) {
//                    state.value = restaurants.restoreSelections()
//                state.value = getAllRestaurants()
//                val restaurants =  respository.getAllRestaurants()
                val restaurants = getInitialRestaurantsUseCase()
                _state.value = _state.value.copy(
                    restaurants = restaurants,
                    isLoading = false
                )
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
        }
    }

    override fun onCleared() {
        super.onCleared()
//        restaurantsCall.cancel()
        // No need to cancel custom coroutines here because we used viewModelScope
        // job.cancel()
    }

    fun toggleFavorite(id: Int, oldValue: Boolean) {
//    fun toggleFavorite(id: Int) {
//        val restaurants = state.value.toMutableList()
//        val itemIndex = restaurants.indexOfFirst { it.id == id }
//        val item = restaurants[itemIndex]
//        restaurants[itemIndex] = item.copy(isFavorite = !item.isFavorite)
//        storeSelection(restaurants[itemIndex])
//        state.value = restaurants
        viewModelScope.launch(errorHandler) {
//            val updatedRestaurants = toggleFavoriteRestaurant(id, item.isFavorite)
//            val updatedRestaurants = respository.toggleFavoriteRestaurant(id, oldValue)
            val updatedRestaurants = toggleRestaurantsUseCase(id, oldValue)
            _state.value = _state.value.copy(
                restaurants = updatedRestaurants
            )
        }
    }

//    fun storeSelection(item: Restaurant) {
//        val savedToggled = stateHandle
//            .get<List<Int>?>(FAVORITES)
//            .orEmpty()
//            .toMutableList()
//        if (item.isFavorite) savedToggled.add(item.id)
//        else savedToggled.remove(item.id)
//        stateHandle[FAVORITES] = savedToggled
//    }

//    companion object {
//        const val FAVORITES = "favorites"
//    }

//    private fun List<Restaurant>.restoreSelections(): List<Restaurant> {
//        stateHandle.get<List<Int>?>(FAVORITES)?.let { selectedIds ->
//            val restaurantsMap = this.associateBy { it.id }
//                .toMutableMap()
//            selectedIds.forEach {id ->
//                val restaurant = restaurantsMap[id] ?: return@forEach
//                restaurantsMap[id] = restaurant.copy(isFavorite = true)
//            }
//            return restaurantsMap.values.toList()
//        }
//        return this
//    }
}