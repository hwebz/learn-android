package com.hado.jetpackkotlinfundamentals

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// The reason we need SavedStateHandle is when app running in background
// but system will kill our process to save the memory
// so all of our favorite restaurants are being cleared, we need to store it
class RestaurantsViewModel(private val stateHandle: SavedStateHandle): ViewModel() {
    private var restInterface: RestaurantsApiService
//    val state = mutableStateOf(dummyRestaurants.restoreSelections())
    val state = mutableStateOf(emptyList<Restaurant>())
    private lateinit var restaurantsCall: Call<List<Restaurant>>

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
        restaurantsCall = restInterface.getRestaurants()
        restaurantsCall.enqueue(
            object: Callback<List<Restaurant>> {
                override fun onResponse(
                    call: Call<List<Restaurant>>,
                    response: Response<List<Restaurant>>
                ) {
                    response.body()?.let { restaurants ->
                        state.value = restaurants.restoreSelections()
                    }
                }

                override fun onFailure(call: Call<List<Restaurant>>, t: Throwable) {
                    t.printStackTrace()
                }
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        restaurantsCall.cancel()
    }

    fun toggleFavorite(id: Int) {
        val restaurants = state.value.toMutableList()
        val itemIndex = restaurants.indexOfFirst { it.id == id }
        val item = restaurants[itemIndex]
        restaurants[itemIndex] = item.copy(isFavorite = !item.isFavorite)
        storeSelection(restaurants[itemIndex])
        state.value = restaurants
    }

    fun storeSelection(item: Restaurant) {
        val savedToggled = stateHandle
            .get<List<Int>?>(FAVORITES)
            .orEmpty()
            .toMutableList()
        if (item.isFavorite) savedToggled.add(item.id)
        else savedToggled.remove(item.id)
        stateHandle[FAVORITES] = savedToggled
    }

    companion object {
        const val FAVORITES = "favorites"
    }

    private fun List<Restaurant>.restoreSelections(): List<Restaurant> {
        stateHandle.get<List<Int>?>(FAVORITES)?.let { selectedIds ->
            val restaurantsMap = this.associateBy { it.id }
            selectedIds.forEach {id ->
                restaurantsMap[id]?.isFavorite = true
            }
            return restaurantsMap.values.toList()
        }
        return this
    }
}