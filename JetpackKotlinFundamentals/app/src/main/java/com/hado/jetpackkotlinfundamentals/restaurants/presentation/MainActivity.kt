package com.hado.jetpackkotlinfundamentals.restaurants.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.hado.jetpackkotlinfundamentals.restaurants.presentation.details.RestaurantDetailsScreen
import com.hado.jetpackkotlinfundamentals.restaurants.presentation.list.RestaurantScreen
import com.hado.jetpackkotlinfundamentals.ui.theme.JetpackKotlinFundamentalsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackKotlinFundamentalsTheme {
//                CoreJetpackComposables()
//                RestaurantScreen()
//                RestaurantDetailsScreen()
                RestaurantApp()
            }
        }
    }
}

@Composable
private fun RestaurantApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "restaurants") {
        composable(
            route = "restaurants"
        ) {
            RestaurantScreen() {id ->
                navController.navigate("restaurants/$id")
            }
        }
        composable(
            route = "restaurants/{restaurant_id}",
            arguments = listOf(
                navArgument("restaurant_id") {
                    type = NavType.IntType
                }
            ),
            // Simulate deeplink action that allow users to click link on any web page and then redirect them correctly
            // to location they tried on our app, in this example, it's restaurant details screen
            // adb shell am start -W -a android.intent.action.VIEW -d "https://www.restaurantsapp.details.com/2"
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "www.restaurantsapp.details.com/{restaurant_id}"
                }
            )
        ) { navStackEntry ->
            val id = navStackEntry.arguments?.getInt("restaurant_id")
            RestaurantDetailsScreen()
        }
    }
}