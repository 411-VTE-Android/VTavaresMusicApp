package com.example.vtavaresmusicapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import coil3.ImageLoader
import coil3.SingletonImageLoader
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import com.example.vtavaresmusicapp.navigation.Detail
import com.example.vtavaresmusicapp.navigation.Home
import com.example.vtavaresmusicapp.screens.AlbumDetailScreen
import com.example.vtavaresmusicapp.screens.AlbumsScreen
import com.example.vtavaresmusicapp.ui.theme.VTavaresMusicAppTheme
import okhttp3.OkHttpClient

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val imageLoader = ImageLoader.Builder(this)
            .components {
                add(OkHttpNetworkFetcherFactory(callFactory = {
                    OkHttpClient.Builder()
                        .followRedirects(true)
                        .followSslRedirects(true)
                        .addInterceptor { chain ->
                            val request = chain.request().newBuilder()
                                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                                .build()
                            chain.proceed(request)
                        }
                        .build()
                }))
            }
            .build()

        SingletonImageLoader.setSafe { imageLoader }

        setContent {
            VTavaresMusicAppTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Home
                ) {
                    composable<Home> {
                        AlbumsScreen(navController = navController)
                    }
                    composable<Detail> { backStackEntry ->
                        val detail = backStackEntry.toRoute<Detail>()
                        AlbumDetailScreen(navController = navController, id = detail.id)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VTavaresMusicAppTheme {
        Greeting("Android")
    }
}