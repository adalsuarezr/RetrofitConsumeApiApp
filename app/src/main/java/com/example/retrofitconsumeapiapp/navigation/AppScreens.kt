package com.example.retrofitconsumeapiapp.navigation

sealed class AppScreens(val route:String){
    object SplashScreen:AppScreens("splash_Screen")
    object MainScreen:AppScreens("main_screen")
}
