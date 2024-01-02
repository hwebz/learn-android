package com.hado.jetpackkotlinfundamentals

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.net.ConnectException
import java.net.UnknownHostException

// The reason we need SavedStateHandle is when app running in background
// but system will kill our process to save the memory
// so all of our favorite restaurants are being cleared, we need to store it
//class RestaurantsViewModel(private val stateHandle: SavedStateHandle): ViewModel() {
class RestaurantsViewModel(private val stateHandle: SavedStateHandle): ViewModel() {
    private var restInterface: RestaurantsApiService
    private var restaurantsDao = RestaurantsDb.getDaoInstance(RestaurantsApplication.getAppContext())
//    val state = mutableStateOf(dummyRestaurants.restoreSelections())
    val state = mutableStateOf(emptyList<Restaurant>())
//    private lateinit var restaurantsCall: Call<List<Restaurant>>
    val job = Job()
    private val scope = CoroutineScope(job + Dispatchers.IO)
    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
    }

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://kotlinrestaurants-1d5a6-default-rtdb.asia-southeast1.firebasedatabase.app/")
            .build()
        restInterface = retrofit.create(RestaurantsApiService::class.java)
        // Instead of calling API from Composable, calling here to make sure
        // it's only being called once when view model initialized
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
                state.value = getAllRestaurants()
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
        }
    }

    private suspend fun refreshCache() {
        val remoteRestaurants = restInterface.getRestaurants()
        val favoriteRestaurants = restaurantsDao.getAllFavorited()
        restaurantsDao.addAll(remoteRestaurants)
        restaurantsDao.updateAll(
            favoriteRestaurants.map {
                PartialRestaurant(it.id, true)
            }
        )
    }

    private suspend fun getAllRestaurants(): List<Restaurant> {
        return withContext(Dispatchers.IO) {
            try {
                refreshCache()
            } catch (e: Exception) {
                when (e) {
                    is UnknownHostException,
                    is ConnectException,
                    is HttpException -> {
                        if (restaurantsDao.getAll().isEmpty()) {
                            throw Exception("Something went wrong. We have no data")
                        }
                    }
                    else -> throw e
                }
            }
            return@withContext restaurantsDao.getAll()
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
            val updatedRestaurants = toggleFavoriteRestaurant(id, oldValue)
            state.value = updatedRestaurants
        }
    }

    private suspend fun toggleFavoriteRestaurant(id: Int, oldValue: Boolean) =
        withContext(Dispatchers.IO) {
            restaurantsDao.update(
                PartialRestaurant(
                    id = id,
                    isFavorite = !oldValue
                )
            )
            restaurantsDao.getAll()
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