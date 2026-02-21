package com.example.diabetacare.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PlayArrow
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
fun MedicationScreen(
    listObat: List<MedData>, // Menerima data dinamis dari MainActivity
    onBack: () -> Unit,
    onAddClick: () -> Unit,
    onStatusChange: (MedData, Boolean) -> Unit // Fungsi baru untuk memberi tahu anak kalau sudah minum
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Jadwal Minum Obat", fontWeight = FontWeight.Black) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
                    }
                },
                actions = {
                    Icon(Icons.Default.Notifications, null, modifier = Modifier.padding(end = 16.dp))
                }
            )
        },
        floatingActionButton = {
            // Tombol tambah obat raksasa
            LargeFloatingActionButton(
                onClick = onAddClick,
                containerColor = Color(0xFF1E3A8A),
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Add, contentDescription = "Tambah", modifier = Modifier.size(36.dp))
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFF8FAFC))
                .padding(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFEFF6FF))
            ) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.PlayArrow, contentDescription = null, tint = Color(0xFF1E3A8A))
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        "Bapak/Ibu, jika sudah minum obat, mohon dicentang nggih supaya anak di rumah tenang.",
                        fontSize = 14.sp,
                        color = Color(0xFF1E3A8A),
                        lineHeight = 20.sp
                    )
                }
            }

            Text("Daftar Obat Hari Ini", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color(0xFF1E293B))
            Spacer(modifier = Modifier.height(12.dp))

            if (listObat.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Belum ada jadwal obat.\nKlik tombol + di bawah.", color = Color.Gray, fontSize = 16.sp)
                }
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(listObat) { obat ->
                        MedicationItem(
                            data = obat,
                            onCheckedChange = { isChecked -> onStatusChange(obat, isChecked) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MedicationItem(data: MedData, onCheckedChange: (Boolean) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(50.dp),
                shape = RoundedCornerShape(12.dp),
                color = data.color.copy(alpha = 0.1f)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text("💊", fontSize = 24.sp)
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(data.name, fontSize = 20.sp, fontWeight = FontWeight.Black, color = Color(0xFF1E3A8A))
                Text(data.time, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFFEF4444))
                Text(data.instruction, fontSize = 14.sp, color = Color.Gray)
            }

            // Checkbox besar agar mudah ditekan lansia
            Checkbox(
                checked = data.isTaken,
                onCheckedChange = onCheckedChange,
                modifier = Modifier.size(48.dp),
                colors = CheckboxDefaults.colors(checkedColor = Color(0xFF1E3A8A))
            )
        }
    }
}

// Data class dengan status isTaken untuk dipantau anak
data class MedData(
    val name: String,
    val dose: String,
    val time: String,
    val instruction: String,
    val color: Color,
    val isTaken: Boolean = false // Defaultnya belum diminum
)