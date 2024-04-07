package com.productsapp.app.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.productsapp.feature.detail.ui.DetailScreen
import com.productsapp.feature.products.ui.screens.ProductListScreen
import com.productsapp.feature.products.ui.screens.ShowListHistoryScreen

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            ProductListScreen(
                modifier = Modifier.padding(16.dp),
                onNavigateToDetail = { productId ->
                    navController.navigate("detail/${productId}")
                },
                onNavigateToHistory = {
                    navController.navigate("history")
                }
            )
        }

        composable("detail/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.StringType })) {backStackEntry ->
            backStackEntry.arguments?.getString("productId")
                ?.let {
                    DetailScreen(
                        itemCode = it,
                        onClickBack = {
                            navController.navigate("main")
                        })
                }
        }


        composable("detailFromHistory/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.StringType })) {backStackEntry ->
            backStackEntry.arguments?.getString("productId")
                ?.let {
                    DetailScreen(
                        itemCode = it,
                        onClickBack = {
                            navController.navigate("history")
                        })
                }
        }

        composable("history") {
            ShowListHistoryScreen(
                onClickBack = {
                    navController.navigate("main")
                },
                onNavigateToDetail = { productId ->
                    navController.navigate("detailFromHistory/${productId}")
                }
            )
        }

    }
}
