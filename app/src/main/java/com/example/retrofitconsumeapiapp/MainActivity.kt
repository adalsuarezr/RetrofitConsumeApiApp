package com.example.retrofitconsumeapiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.retrofitconsumeapiapp.navigation.AppNavigation
import com.example.retrofitconsumeapiapp.ui.theme.RetrofitConsumeApiAppTheme

class MainActivity : ComponentActivity() {
//Todo
// dagger
// control de errores
// organizar en packages
// delegar responsabilidades del viewmodel
// mejorar las respuestas de los casos de uso
// cambiar la visualizacion del splash screen
// mejorar ui

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            RetrofitConsumeApiAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}
