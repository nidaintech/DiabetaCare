package com.example.diabetacare.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class InfoObat(
    val kategori: String,
    val contoh: String,
    val penjelasan: String,
    val warnaKategori: Color
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicationInfoScreen(onBack: () -> Unit) {
    val daftarInfo = listOf(
        InfoObat(
            "💊 Obat Apotek (Medis)",
            "Amlodipine, Captopril, Valsartan",
            "Obat ini bekerja cepat menurunkan tensi. Harus diminum rutin sesuai resep dokter. Jangan berhenti tiba-tiba ya Pak/Bu!",
            Color(0xFF3B82F6)
        ),
        InfoObat(
            "🌿 Obat Herbal (Alami)",
            "Seledri, Mentimun, Daun Salam",
            "Bagus untuk pendamping, tapi bukan pengganti obat dokter. Seledri membantu membuang garam lewat air seni.",
            Color(0xFF10B981)
        ),
        InfoObat(
            "⚠️ Peringatan Penting",
            "Konsultasi adalah Kunci",
            "Jangan minum obat apotek berbarengan dengan herbal tanpa tanya bidan atau dokter, supaya tidak pusing atau lemas.",
            Color(0xFFEF4444)
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Info Obat Hipertensi", fontWeight = FontWeight.Black) },
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
            item {
                Text(
                    "Mengenal Obat\n& Tanaman Sehat",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF1E3A8A)
                )
            }

            items(daftarInfo) { info ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Surface(
                            color = info.warnaKategori.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = info.kategori,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                color = info.warnaKategori,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Text("Contoh: ${info.contoh}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(info.penjelasan, fontSize = 16.sp, color = Color.Gray, lineHeight = 24.sp)
                    }
                }
            }
        }
    }
}