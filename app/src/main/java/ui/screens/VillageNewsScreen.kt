package com.example.diabetacare.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VillageNewsScreen(onBack: () -> Unit) {
    val daftarBerita = listOf(
        NewsData("Cek Tensi Gratis", "Besok pagi di Balai Desa Setono jam 08.00 WIB.", "Penting", Color(0xFFEF4444)),
        NewsData("Tips Senam Lansia", "Video gerakan senam ringan untuk menjaga otot.", "Edukasi", Color(0xFF3B82F6)),
        NewsData("Bantuan Vitamin", "Pembagian vitamin gratis bagi warga lansia hari Jumat.", "Info", Color(0xFF10B981))
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Kabar Desa Setono", fontWeight = FontWeight.Black) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFF8FAFC))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(daftarBerita) { berita ->
                NewsItem(berita)
            }
        }
    }
}

@Composable
fun NewsItem(data: NewsData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Surface(
                color = data.tagColor.copy(alpha = 0.1f),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = data.tag,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    color = data.tagColor,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(data.title, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E3A8A))
            Spacer(modifier = Modifier.height(4.dp))
            Text(data.desc, fontSize = 14.sp, color = Color.DarkGray)

            Spacer(modifier = Modifier.height(12.dp))

            TextButton(
                onClick = { /* Buka Detail */ },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Baca Selengkapnya >", color = data.tagColor)
            }
        }
    }
}

data class NewsData(val title: String, val desc: String, val tag: String, val tagColor: Color)