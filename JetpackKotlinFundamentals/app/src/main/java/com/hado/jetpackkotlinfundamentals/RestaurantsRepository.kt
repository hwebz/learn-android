package com.hado.jetpackkotlinfundamentals

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.net.ConnectException
import java.net.UnknownHostException

class RestaurantsRepository {
    private var restInterface: RestaurantsApiService = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://kotlinrestaurants-1d5a6-default-rtdb.asia-southeast1.firebasedatabase.app/")
        .build()
        .create(RestaurantsApiService::class.java)
    private var restaurantsDao = RestaurantsDb.getDaoInstance(RestaurantsApplication.getAppContext())

    private suspend fun refreshCache() {
        val remoteRestaurants = restInterface.getRestaurants()
        val favoriteRestaurants = restaurantsDao.getAllFavorited()
        restaurantsDao.addAll(remoteRestaurants.map {
            LocalRestaurant(
                it.id,
                it.title,
                it.description,
                false
            )
        })
        restaurantsDao.updateAll(
            favoriteRestaurants.map {
                PartialLocalRestaurant(it.id, true)
            }
        )
    }

//    suspend fun getAllRestaurants(): List<Restaurant> {
    suspend fun loadRestaurants() {
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
//            return@withContext restaurantsDao.getAll()
//                .sortedBy { it.title }
        }
    }

//    suspend fun toggleFavoriteRestaurant(id: Int, oldValue: Boolean) =
    suspend fun toggleFavoriteRestaurant(id: Int, value: Boolean) =
        withContext(Dispatchers.IO) {
            restaurantsDao.update(
                PartialLocalRestaurant(
                    id = id,
//                    isFavorite = !oldValue
                    isFavorite = value
                )
            )
//            restaurantsDao.getAll()

        }

    suspend fun getRestaurants(): List<Restaurant> {
        return withContext(Dispatchers.IO) {
            return@withContext restaurantsDao.getAll().map {
                Restaurant(it.id, it.title, it.description, it.isFavorite)
            }
        }
    }
}