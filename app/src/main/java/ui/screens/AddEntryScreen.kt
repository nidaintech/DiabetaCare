package com.example.diabetacare.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEntryScreen(onBack: () -> Unit) {
    // 1. State Management
    var bloodSugarValue by remember { mutableStateOf("") }
    var selectedCondition by remember { mutableStateOf("Puasa") }
    var note by remember { mutableStateOf("") }

    // 2. Logika Status Gula Darah Otomatis
    val sugarLevel = bloodSugarValue.toIntOrNull() ?: 0
    val statusText = when {
        bloodSugarValue.isEmpty() -> ""
        sugarLevel < 70 -> "Status: Terlalu Rendah (Bahaya)"
        sugarLevel in 70..130 -> "Status: Normal (Bagus!)"
        sugarLevel in 131..180 -> "Status: Pra-Diabetes (Waspada)"
        else -> "Status: Tinggi (Konsultasi ke Dokter)"
    }
    val statusColor = when {
        sugarLevel in 70..130 -> Color(0xFF16A34A) // Hijau
        sugarLevel in 131..180 -> Color(0xFFCA8A04) // Kuning
        else -> Color(0xFFDC2626) // Merah
    }

    // 3. Info Tanggal
    val sdf = SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("id-ID"))
    val currentDate = sdf.format(Date())
    val conditions = listOf("Puasa", "Sebelum Makan", "Sesudah Makan", "Sebelum Tidur")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Catat Gula Darah", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFF8FAFC))
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // --- BAGIAN 1: INFO TANGGAL ---
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.DateRange, contentDescription = null, tint = Color(0xFF2563EB))
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = "Tanggal: $currentDate", fontWeight = FontWeight.Medium)
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
            Text("Berapa angka gula darah Anda?", fontSize = 16.sp, color = Color.Gray)

            // --- BAGIAN 2: INPUT ANGKA (DINAMIS) ---
            OutlinedTextField(
                value = bloodSugarValue,
                onValueChange = { if (it.length <= 3 && it.all { char -> char.isDigit() }) bloodSugarValue = it },
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Black,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF1E40AF)
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.width(220.dp),
                shape = RoundedCornerShape(20.dp),
                singleLine = true
            )

            // Indikator Status Langsung
            if (bloodSugarValue.isNotEmpty()) {
                Text(
                    text = statusText,
                    color = statusColor,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            Text("mg/dL", fontWeight = FontWeight.Bold, color = Color.Gray)

            Spacer(modifier = Modifier.height(32.dp))

            // --- BAGIAN 3: KONDISI TUBUH ---
            Text("Kapan pengecekan dilakukan?", fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Start))
            Spacer(modifier = Modifier.height(12.dp))
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                conditions.chunked(2).forEach { row ->
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        row.forEach { item ->
                            val isSelected = selectedCondition == item
                            Button(
                                onClick = { selectedCondition = item },
                                modifier = Modifier.weight(1f).height(48.dp),
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (isSelected) Color(0xFF2563EB) else Color.White,
                                    contentColor = if (isSelected) Color.White else Color.DarkGray
                                ),
                                border = if (!isSelected) BorderStroke(1.dp, Color.LightGray) else null
                            ) {
                                Text(item, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // --- BAGIAN 4: CATATAN ---
            OutlinedTextField(
                value = note,
                onValueChange = { note = it },
                label = { Text("Catatan (Misal: Selesai Olahraga)") },
                modifier = Modifier.fillMaxWidth().height(100.dp),
                shape = RoundedCornerShape(16.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))

            // --- BAGIAN 5: TOMBOL SIMPAN (VALIDASI) ---
            Button(
                onClick = {
                    // Logika penyimpanan data akan diintegrasikan di sini
                    onBack()
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                enabled = bloodSugarValue.isNotEmpty(), // TOMBOL HANYA AKTIF JIKA DIISI
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2563EB),
                    disabledContainerColor = Color.LightGray
                )
            ) {
                Text("SIMPAN CATATAN", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}