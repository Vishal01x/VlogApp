package com.exa.android.vlogassignment.navigation

import android.widget.Toast
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.exa.android.vlogassignment.presentation.VlogDetail.VlogDetailScreen
import com.exa.android.vlogassignment.presentation.VlogDetail.WebViewScreen
import com.exa.android.vlogassignment.presentation.VlogScreen.VlogScreen
import com.exa.android.vlogassignment.presentation.VlogScreen.viewModel.VlogViewModel
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.VlogScreen.route) {
        composable(Screen.VlogScreen.route) {
            val context = LocalContext.current
            val viewModel = hiltViewModel<VlogViewModel>()
            val vlogs = viewModel.vlogPagingFlow.collectAsLazyPagingItems()
            VlogScreen(vlogs = vlogs) { url ->
                url?.let {
                    navController.navigate(Screen.WebViewScreen.createRoute(it))
                } ?: run { // only when user clicks avoid recomposition
                    Toast.makeText(context, "Content is not available", Toast.LENGTH_SHORT).show()
                }
            }
        }
        composable(
            route = Screen.WebViewScreen.route,
            arguments = listOf(navArgument("url") { type = NavType.StringType })
        ) { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url")?.let {
                URLDecoder.decode(it, StandardCharsets.UTF_8.toString())
            } ?: ""
            VlogDetailScreen(url = url){
                navController.popBackStack()
            }
        }

    }
}
