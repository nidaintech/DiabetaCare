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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 1. PINDAHKAN DATA CLASS KE PALING ATAS (Agar tidak Unresolved Reference)
data class EdukasiData(
    val judul: String,
    val isi: String,
    val warna: Color
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EducationScreen(onBack: () -> Unit) {
    // 2. PASTIIN LIST DATA PAKAI TIPE DATA YANG JELAS
    val daftarEdukasi: List<EdukasiData> = listOf(
        EdukasiData(
            judul = "✅ Yang Diperbolehkan",
            isi = "• Sayuran Hijau: Bayam, sawi, dan kangkung.\n• Buah-buahan: Pisang dan semangka.\n• Ikan & Ayam: Rebus atau kukus.\n• Jalan Pagi: Cukup 15-30 menit setiap pagi.",
            warna = Color(0xFF10B981)
        ),
        EdukasiData(
            judul = "❌ Larangan (Pantangan)",
            isi = "• Garam Berlebih: Hindari ikan asin dan kecap asin.\n• Makanan Kaleng: Mi instan dan sarden.\n• Lemak Jahat: Jeroan dan daging kambing.\n• Rokok & Kopi Kental: Bikin jantung berdebar kencang.",
            warna = Color(0xFFEF4444)
        ),
        EdukasiData(
            judul = "💧 Cara Minum Air Putih",
            isi = "• 8 Gelas Sehari: Membantu membuang garam tubuh.\n• Minum Hangat: Bagus untuk sirkulasi darah pagi.\n• Jangan Tunggu Haus: Lansia wajib dijadwal minumnya.",
            warna = Color(0xFF3B82F6)
        ),
        EdukasiData(
            judul = "😴 Istirahat & Pikiran",
            isi = "• Tidur Teratur: Sebelum jam 10 malam.\n• Kurangi Begadang: Memicu hormon stres naik.\n• Jaga Hati: Hati tenang adalah obat tensi paling ampuh.",
            warna = Color(0xFF8B5CF6)
        ),
        EdukasiData(
            judul = "🏥 Periksa Rutin",
            isi = "• Catat Tensi: Minimal seminggu sekali di Posyandu.\n• Minum Obat: Jangan berhenti tanpa izin dokter.",
            warna = Color(0xFFF59E0B)
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edukasi Sehat", fontWeight = FontWeight.Black) },
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
                    text = "Panduan Hidup Sehat\nKhusus Hipertensi",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF1E3A8A),
                    lineHeight = 32.sp
                )
            }

            // 3. PAKAI VARIABEL YANG SESUAI DENGAN DATA CLASS
            items(daftarEdukasi) { info ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            text = info.judul,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = info.warna
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = info.isi,
                            fontSize = 16.sp,
                            color = Color(0xFF334155),
                            lineHeight = 26.sp
                        )
                    }
                }
            }
            item { Spacer(modifier = Modifier.height(20.dp)) }
        }
    }
}