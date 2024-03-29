package com.hado.jetpackkotlinfundamentals.restaurants.data

import com.hado.jetpackkotlinfundamentals.restaurants.domain.Restaurant
import com.hado.jetpackkotlinfundamentals.RestaurantsApplication
import com.hado.jetpackkotlinfundamentals.restaurants.data.di.IoDispatcher
import com.hado.jetpackkotlinfundamentals.restaurants.data.local.LocalRestaurant
import com.hado.jetpackkotlinfundamentals.restaurants.data.local.PartialLocalRestaurant
import com.hado.jetpackkotlinfundamentals.restaurants.data.local.RestaurantsDao
import com.hado.jetpackkotlinfundamentals.restaurants.data.local.RestaurantsDb
import com.hado.jetpackkotlinfundamentals.restaurants.data.remote.RestaurantsApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestaurantsRepository @Inject constructor(
    private val restInterface: RestaurantsApiService,
    private val restaurantsDao: RestaurantsDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
//    private var restInterface: RestaurantsApiService = Retrofit.Builder()
//        .addConverterFactory(GsonConverterFactory.create())
//        .baseUrl("https://kotlinrestaurants-1d5a6-default-rtdb.asia-southeast1.firebasedatabase.app/")
//        .build()
//        .create(RestaurantsApiService::class.java)
//    private var restaurantsDao = RestaurantsDb.getDaoInstance(RestaurantsApplication.getAppContext())

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
        return withContext(dispatcher) {
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
        withContext(dispatcher) {
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
        return withContext(dispatcher) {
            return@withContext restaurantsDao.getAll().map {
                Restaurant(it.id, it.title, it.description, it.isFavorite)
            }
        }
    }
}