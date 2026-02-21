package com.example.diabetacare

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.example.diabetacare.ui.screens.*
import com.example.diabetacare.UserProfile

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // State untuk navigasi antar layar
            var currentScreen by remember { mutableStateOf("login") }

            // State untuk menyimpan profil user agar bisa tampil di ProfileScreen
            var userAccount by remember { mutableStateOf(UserProfile()) }

            // State List untuk menampung daftar obat secara dinamis
            val listObat = remember { mutableStateListOf<MedData>() }

            when (currentScreen) {
                "login" -> LoginScreen(
                    onNavigateToRegister = { currentScreen = "register" },
                    onLoginSuccess = { currentScreen = "dashboard" }
                )

                "register" -> RegisterScreen(
                    onRegisterSuccess = { namaBaru, emailBaru, umurBaru ->
                        // Data yang diinput bapak/ibu disimpan di sini
                        userAccount = UserProfile(name = namaBaru, email = emailBaru, age = umurBaru)
                        currentScreen = "login"
                    },
                    onNavigateToLogin = { currentScreen = "login" }
                )

                "dashboard" -> DashboardScreen(
                    user = userAccount,
                    onAddClick = { currentScreen = "add_entry" },

                    // --- NAVIGASI BERBEDA (PENTING!) ---
                    // 1. Klik di Menu Grid Dashboard -> Ke halaman Ceklis Obat
                    onMedicationClick = { currentScreen = "medication" },

                    // 2. Klik di Navbar Bawah -> Ke halaman Edukasi Herbal/Apotek
                    onNavbarObatClick = { currentScreen = "medication_info" },

                    onVillageNewsClick = { currentScreen = "village_news" },
                    onEducationClick = { currentScreen = "education" },
                    onKaderClick = { currentScreen = "kader" },
                    onAIClick = { /* Menu AI Vision */ },
                    onHistoryClick = { currentScreen = "history" },
                    onLogout = { currentScreen = "profile" }
                )

                // Halaman Edukasi Obat (Apotek & Herbal)
                "medication_info" -> MedicationInfoScreen(
                    onBack = { currentScreen = "dashboard" }
                )

                // Halaman Manajemen Jadwal Obat (Ceklis)
                "medication" -> MedicationScreen(
                    listObat = listObat,
                    onBack = { currentScreen = "dashboard" },
                    onAddClick = { currentScreen = "add_medication" },
                    onStatusChange = { obat, status ->
                        val index = listObat.indexOf(obat)
                        if (index != -1) {
                            listObat[index] = obat.copy(isTaken = status)
                        }
                    }
                )

                "add_medication" -> AddMedicationScreen(
                    onBack = { currentScreen = "medication" },
                    onSave = { nama, jam, aturan ->
                        listObat.add(MedData(nama, "1x Sehari", jam, aturan, Color.Blue))
                        currentScreen = "medication"
                    }
                )

                "kader" -> KaderScreen(onBack = { currentScreen = "dashboard" })
                "education" -> EducationScreen(onBack = { currentScreen = "dashboard" })
                "village_news" -> VillageNewsScreen(onBack = { currentScreen = "dashboard" })
                "add_entry" -> AddEntryScreen(onBack = { currentScreen = "dashboard" })
                "history" -> HistoryScreen(onBack = { currentScreen = "dashboard" })

                "profile" -> ProfileScreen(
                    user = userAccount, // Mengirim data user ke layar profil
                    onBack = { currentScreen = "dashboard" },
                    onLogout = { currentScreen = "login" }
                )
            }
        }
    }
}