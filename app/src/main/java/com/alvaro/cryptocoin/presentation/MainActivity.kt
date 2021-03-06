package com.alvaro.cryptocoin.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alvaro.cryptocoin.common.Constants
import com.alvaro.cryptocoin.presentation.coin_detail.CoinDetailScreen
import com.alvaro.cryptocoin.presentation.coin_list.CoinListScreen
import com.alvaro.cryptocoin.presentation.ui.theme.CryptoCoinTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoCoinTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    //Greeting("Android")
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CoinListScreen.route
                    ) {
                        composable(
                            route = Screen.CoinListScreen.route
                        ){
                            CoinListScreen( navController )
                        }

                        composable(
                            route = Screen.CoinDetailScreen.route + "/{${Constants.COIN_ID_KEY}}"
                        ){
                            CoinDetailScreen()
                        }
                    }
                }
            }
        }
    }
}

/*@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CryptoCoinTheme {
        Greeting("Android")
    }
}*/