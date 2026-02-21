package com.example.diabetacare.ui.screens

// IMPORT WAJIB: Tambahkan baris ini supaya BorderStroke tidak error lagi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diabetacare.R

// Data model Kader tetap di sini agar rapi
data class KaderData(
    val nama: String,
    val peran: String,
    val rw: String,
    val fotoRes: Int,
    val statusColor: Color
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KaderScreen(onBack: () -> Unit) {
    val daftarKader = listOf(
        // Pastikan gambar img_kader1, 2, dan 3 sudah ada di folder drawable
        KaderData("Ibu Siti Aminah", "Ketua Kader Posyandu", "RW 01 - Dusun Setono", R.drawable.img_kader1, Color.Green),
        KaderData("Ibu Ratna Sari", "Kader Pendamping Lansia", "RW 02 - Dusun Setono", R.drawable.img_kader2, Color.Green),
        KaderData("Ibu Herawati", "Kader Kesehatan Lingkungan", "RW 03 - Dusun Setono", R.drawable.img_kader3, Color.Yellow)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Bantuan Kader", fontWeight = FontWeight.Bold, color = Color(0xFF1E3A8A)) },
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
            items(daftarKader) { kader ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = kader.fotoRes),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(CircleShape)
                                    .border(2.dp, Color.LightGray, CircleShape),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text(kader.nama, fontWeight = FontWeight.Black, fontSize = 18.sp, color = Color(0xFF1E3A8A))
                                Text(kader.peran, fontSize = 14.sp, color = Color(0xFF22C55E), fontWeight = FontWeight.Bold)
                                Text(kader.rw, fontSize = 12.sp, color = Color.Gray)
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))

                        // BAGIAN INI YANG TADI ERROR: Sekarang sudah aman
                        Button(
                            onClick = { /* Buka WA */ },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF0FDF4)),
                            border = BorderStroke(1.dp, Color(0xFF22C55E)),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Icon(Icons.Default.Call, contentDescription = null, tint = Color(0xFF22C55E))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Hubungi via WhatsApp", color = Color(0xFF22C55E), fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}