package com.example.diabetacare.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
// --- IMPORT MODEL USER ---
import com.example.diabetacare.UserProfile

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    user: UserProfile, // Data ini dikirim dari MainActivity
    onBack: () -> Unit,
    onLogout: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profil Saya", fontWeight = FontWeight.Bold, color = Color(0xFF1E3A8A)) },
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
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // AVATAR DENGAN INISIAL NAMA
            Box(
                modifier = Modifier
                    .size(110.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF2563EB)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (user.name.isNotEmpty()) user.name.take(1).uppercase() else "?",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // MENAMPILKAN DATA DARI LOGIN/REGISTER
            Text(
                text = user.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF1E293B)
            )
            Text(
                text = user.email,
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))

            // KARTU INFORMASI UTAMA
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(2.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Umur", fontSize = 12.sp, color = Color.Gray)
                        Text("${user.age} Thn", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF2563EB))
                    }
                    VerticalDivider(modifier = Modifier.height(40.dp).width(1.dp), color = Color.LightGray)
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Kesehatan", fontSize = 12.sp, color = Color.Gray)
                        Text("Hipertensi", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF10B981))
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // MENU PENGATURAN
            UserMenuItem(Icons.Default.Edit, "Ubah Data Diri") { }
            UserMenuItem(Icons.Default.Settings, "Pengingat Jadwal") { }
            UserMenuItem(
                icon = Icons.AutoMirrored.Filled.ExitToApp,
                title = "Keluar dari Akun",
                isDanger = true,
                onClick = onLogout // Menjalankan fungsi logout
            )
        }
    }
}

@Composable
fun UserMenuItem(
    icon: ImageVector,
    title: String,
    isDanger: Boolean = false,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        border = BorderStroke(1.dp, Color(0xFFE2E8F0))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (isDanger) Color.Red else Color.Gray
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                color = if (isDanger) Color.Red else Color.Black,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.LightGray
            )
        }
    }
}