package com.example.retrofitconsumeapiapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.retrofitconsumeapiapp.MainScreen
import com.example.retrofitconsumeapiapp.MyViewModel
import com.example.retrofitconsumeapiapp.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    var viewModel = MyViewModel()
    NavHost(
        navController = navController,
        startDestination = AppScreens.SplashScreen.route){
        composable(AppScreens.SplashScreen.route){
            SplashScreen(navController, viewModel)
        }
        composable(AppScreens.MainScreen.route){
            MainScreen(navController, viewModel)
        }
    }
}