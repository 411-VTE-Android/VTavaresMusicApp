package com.example.vtavaresmusicapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.vtavaresmusicapp.components.AlbumLargeCard
import com.example.vtavaresmusicapp.components.AlbumSmallCard
import com.example.vtavaresmusicapp.components.HomeHeader
import com.example.vtavaresmusicapp.components.MiniPlayer
import com.example.vtavaresmusicapp.components.SectionHeader
import com.example.vtavaresmusicapp.models.Album
import com.example.vtavaresmusicapp.navigation.Detail
import com.example.vtavaresmusicapp.services.AlbumsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun AlbumsScreen(navController: NavController) {
    val BASE_URL = "https://musicapi.pjasoft.com/"
    var albums by remember { mutableStateOf(listOf<Album>()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(key1 = true) {
        try {
            val retrofitBuilder = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val result = async(Dispatchers.IO) {
                val service = retrofitBuilder.create(AlbumsService::class.java)
                service.getAllAlbums()
            }

            albums = result.await()
            isLoading = false
        } catch (e: Exception) {
            errorMessage = "Error de conexión"
            isLoading = false
        }
    }

    Scaffold(
        bottomBar = { MiniPlayer(album = albums.firstOrNull()) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF0EBF8))
                .padding(innerPadding)
        ) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (errorMessage != null) {
                Text(text = errorMessage!!, modifier = Modifier.align(Alignment.Center))
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    item { HomeHeader(name = "Vic") }

                    item {
                        SectionHeader(title = "Albums")
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 20.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(albums) { album ->
                                AlbumLargeCard(album = album) {
                                    navController.navigate(Detail(id = album.id))
                                }
                            }
                        }
                    }

                    item { SectionHeader(title = "Recently Played") }

                    items(albums) { album ->
                        Box(modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)) {
                            AlbumSmallCard(album = album) {
                                navController.navigate(Detail(id = album.id))
                            }
                        }
                    }
                }
            }
        }
    }
}
