package com.example.vtavaresmusicapp.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vtavaresmusicapp.components.AboutAlbum
import com.example.vtavaresmusicapp.components.AlbumDetailHeader
import com.example.vtavaresmusicapp.components.AlbumSmallCard
import com.example.vtavaresmusicapp.components.DetailActionButtons
import com.example.vtavaresmusicapp.components.MiniPlayer
import com.example.vtavaresmusicapp.models.Album
import com.example.vtavaresmusicapp.services.AlbumsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun AlbumDetailScreen(navController: NavController, id: String) {
    val BASE_URL = "https://musicapi.pjasoft.com/"
    var album by remember { mutableStateOf<Album?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(key1 = id) {
        try {
            val retrofitBuilder = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val result = async(Dispatchers.IO) {
                val service = retrofitBuilder.create(AlbumsService::class.java)
                service.getAlbumById(id)
            }

            album = result.await()
            isLoading = false
        } catch (e: Exception) {
            Log.e("AlbumDetail", "Error: ${e.message}")
            errorMessage = "No se pudo obtener el detalle"
            isLoading = false
        }
    }

    Scaffold(
        bottomBar = { MiniPlayer(album = album) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF0EBF8))
                .padding(innerPadding)
        ) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center), color = Color(0xFF8E6CEF))
            } else if (errorMessage != null) {
                Text(text = errorMessage!!, color = Color.Red, modifier = Modifier.align(Alignment.Center))
            } else if (album != null) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    item {
                        AlbumDetailHeader(album = album!!) {
                            navController.popBackStack()
                        }
                    }

                    item { DetailActionButtons() }

                    item { AboutAlbum(description = album!!.description, artist = album!!.artist) }

                    item {
                        Text(
                            text = "Tracks",
                            modifier = Modifier.padding(start = 20.dp, top = 20.dp, bottom = 8.dp),
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }
                    items(10) { index ->
                        Box(modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)) {
                            AlbumSmallCard(
                                album = album!!,
                                trackTitle = "${album!!.title} • Track ${index + 1}"
                            ) {}
                        }
                    }
                    item { Spacer(modifier = Modifier.height(80.dp)) }
                }
            }
        }
    }
}