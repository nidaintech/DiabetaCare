package com.example.diabetacare

data class UserProfile(
    val name: String = "User Baru",
    val email: String = "user@mail.com",
    val age: String = "-",
    val weight: String = "-",
    val diabetesType: String = "Tipe 2", // Pastikan ada baris ini!
    val university: String = "UNIDA Gontor",
    val major: String = "Teknik Informatika"
)