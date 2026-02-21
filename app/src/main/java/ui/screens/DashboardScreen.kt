package com.example.diabetacare.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diabetacare.UserProfile

@Composable
fun DashboardScreen(
    user: UserProfile,
    onAddClick: () -> Unit,
    onMedicationClick: () -> Unit,      // Grid: Ke Manajemen Jadwal
    onNavbarObatClick: () -> Unit,      // Navbar: Ke Edukasi Info Obat
    onVillageNewsClick: () -> Unit,
    onEducationClick: () -> Unit,
    onKaderClick: () -> Unit,
    onAIClick: () -> Unit,
    onHistoryClick: () -> Unit,
    onLogout: () -> Unit
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Si-Tono Sehat",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black,
                    color = Color(0xFF1E3A8A)
                )
                Row {
                    Icon(Icons.Default.Notifications, "Suara", tint = Color(0xFF1E3A8A))
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(Icons.Default.AccountCircle, "Profil", tint = Color(0xFF1E3A8A))
                }
            }
        },
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                NavigationBarItem(selected = true, onClick = {}, icon = { Icon(Icons.Default.Home, "Utama") }, label = { Text("Utama") })

                // PERBAIKAN: Navbar diarahkan ke Info Obat (Herbal & Apotek)
                NavigationBarItem(
                    selected = false,
                    onClick = onNavbarObatClick,
                    icon = { Icon(Icons.Default.DateRange, "Obat") },
                    label = { Text("Obat") }
                )

                NavigationBarItem(selected = false, onClick = onKaderClick, icon = { Icon(Icons.Default.Person, "Posyandu") }, label = { Text("Posyandu") })
                NavigationBarItem(selected = false, onClick = onLogout, icon = { Icon(Icons.AutoMirrored.Filled.List, "Profil") }, label = { Text("Profil") })
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color.White)
                .padding(horizontal = 20.dp)
        ) {
            Text("Selamat Pagi,", fontSize = 16.sp, color = Color.Gray)
            Text("Halo, ${user.name}", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E3A8A))

            Spacer(modifier = Modifier.height(16.dp))

            Surface(
                color = Color(0xFFEFF6FF),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color(0xFF3B82F6))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Hari ini Anda sudah minum obat pagi. Luar biasa!",
                        fontSize = 13.sp,
                        color = Color(0xFF1E3A8A),
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF0FDF4))
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Surface(color = Color(0xFFDCFCE7), shape = RoundedCornerShape(8.dp)) {
                            Text("Tensi Terakhir", modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp), color = Color(0xFF166534), fontSize = 12.sp)
                        }
                        Text("Baru Saja", fontSize = 12.sp, color = Color.Gray)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "140/90", fontSize = 48.sp, fontWeight = FontWeight.Black, color = Color(0xFF1E3A8A))
                    Text("mmHg", fontSize = 18.sp, color = Color(0xFF1E3A8A))
                    Text("Status: Sedikit Tinggi", fontWeight = FontWeight.Bold, color = Color(0xFF16A34A))
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.weight(1f)
            ) {
                item { MenuButton("Catat Tensi", "Periksa Sekarang", Icons.Default.Favorite, Color(0xFFF0FDF4), Color(0xFF22C55E), onAddClick) }

                // GRID TETAP KE JADWAL OBAT (Daftar Ceklis)
                item { MenuButton("Jadwal Obat", "Lihat Jam Minum", Icons.Default.Add, Color(0xFFEFF6FF), Color(0xFF3B82F6), onMedicationClick) }

                item { MenuButton("Kabar Desa", "Info Posyandu", Icons.Default.Notifications, Color(0xFFFFFBEB), Color(0xFFF59E0B), onVillageNewsClick) }
                item { MenuButton("Edukasi Sehat", "Tips Hipertensi", Icons.Default.Info, Color(0xFFF5F3FF), Color(0xFF8B5CF6), onEducationClick) }
            }

            Text(
                text = "Jika Bapak/Ibu merasa pusing atau butuh bantuan kesehatan segera, tekan tombol di bawah ini:",
                fontSize = 13.sp,
                color = Color.Red,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )

            Button(
                onClick = {
                    val nomorKader = "628123456789"
                    val pesan = "BANTUAN DARURAT! Bapak/Ibu ${user.name} membutuhkan bantuan kesehatan segera."
                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse("https://api.whatsapp.com/send?phone=$nomorKader&text=${Uri.encode(pesan)}")
                    }
                    context.startActivity(intent)
                },
                modifier = Modifier.fillMaxWidth().height(80.dp).padding(bottom = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEF4444)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(Icons.Default.Warning, contentDescription = null, tint = Color.White)
                Spacer(modifier = Modifier.width(12.dp))
                Text("TOMBOL DARURAT", fontSize = 20.sp, fontWeight = FontWeight.Black, color = Color.White)
            }
        }
    }
}

@Composable
fun MenuButton(title: String, desc: String, icon: ImageVector, bgColor: Color, iconColor: Color, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().height(160.dp).clickable { onClick() },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor)
    ) {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Surface(color = Color.White, shape = RoundedCornerShape(12.dp)) {
                Icon(icon, null, modifier = Modifier.size(40.dp).padding(8.dp), tint = iconColor)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(title, fontWeight = FontWeight.Bold, color = iconColor, fontSize = 18.sp)
            Text(desc, fontSize = 11.sp, color = iconColor.copy(alpha = 0.7f))
        }
    }
}