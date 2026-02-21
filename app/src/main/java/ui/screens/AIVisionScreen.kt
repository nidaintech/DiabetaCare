package com.example.diabetacare.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AIVisionScreen(onBack: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        // Simulasi Tampilan Kamera
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(Icons.Default.Search, contentDescription = null, tint = Color.White, modifier = Modifier.size(100.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Text("Menganalisis Makanan...", color = Color.White)
            Text("AI sedang menghitung beban glikemik", color = Color.LightGray, style = MaterialTheme.typography.bodySmall)
        }

        Button(
            onClick = onBack,
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 50.dp)
        ) {
            Text("Kembali ke Dashboard")
        }
    }
}