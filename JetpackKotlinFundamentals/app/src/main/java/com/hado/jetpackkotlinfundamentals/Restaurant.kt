package com.hado.jetpackkotlinfundamentals

import com.google.gson.annotations.SerializedName

data class Restaurant(
    @SerializedName("r_id")
    val id: Int,
    @SerializedName("r_title")
    val title: String,
    @SerializedName("r_description")
    val description: String,
    var isFavorite: Boolean = false
)

//val dummyRestaurants = listOf<Restaurant>(
//    Restaurant(0, "Alfredo foods", "At Alfredo's description"),
//    Restaurant(1, "China foods", "At China's description"),
//    Restaurant(2, "Singapore foods", "At Singapore's description"),
//    Restaurant(3, "Japan foods", "At Japan's description"),
//    Restaurant(4, "Indonesia foods", "At Indonesia's description"),
//    Restaurant(5, "Thailand foods", "At Thailand's description"),
//    Restaurant(6, "Vietnam foods", "At Vietnam's description"),
//    Restaurant(7, "South Korea foods", "At South Korea's description"),
//    Restaurant(8, "Australia foods", "At Australia's description"),
//    Restaurant(9, "Malaysia foods", "At Malaysia's description"),
//    Restaurant(6, "England foods", "At England's description"),
//    Restaurant(7, "USA foods", "At USA's description"),
//    Restaurant(8, "Cuba foods", "At Cuba's description"),
//    Restaurant(9, "Brazil foods", "At Brazil's description")
//)