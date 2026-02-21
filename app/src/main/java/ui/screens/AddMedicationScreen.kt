package com.example.diabetacare.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMedicationScreen(onBack: () -> Unit, onSave: (String, String, String) -> Unit) {
    var namaObat by remember { mutableStateOf("") }
    var jamMinum by remember { mutableStateOf("") }

    // Pilihan aturan makan
    val radioOptions = listOf("Sebelum Makan", "Sesudah Makan")
    var selectedOption by remember { mutableStateOf(radioOptions[1]) }

    var showTimePicker by remember { mutableStateOf(false) }
    val timePickerState = rememberTimePickerState(is24Hour = true)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tambah Obat Baru", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(20.dp)) {
            Text("Nama Obat", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            OutlinedTextField(
                value = namaObat,
                onValueChange = { namaObat = it },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                placeholder = { Text("Contoh: Amlodipine") },
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Jam Minum", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            OutlinedTextField(
                value = jamMinum,
                onValueChange = { },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { showTimePicker = true },
                placeholder = { Text("Klik untuk pilih jam") },
                trailingIcon = { Icon(Icons.Default.DateRange, null) },
                shape = RoundedCornerShape(12.dp),
                enabled = false,
                colors = OutlinedTextFieldDefaults.colors(
                    disabledTextColor = Color.Black,
                    disabledBorderColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Aturan Minum", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Column(modifier = Modifier.padding(vertical = 8.dp)) {
                radioOptions.forEach { text ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (text == selectedOption),
                                onClick = { selectedOption = text }
                            )
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (text == selectedOption),
                            onClick = { selectedOption = text }
                        )
                        Text(text = text, fontSize = 18.sp, modifier = Modifier.padding(start = 12.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if(namaObat.isNotEmpty() && jamMinum.isNotEmpty()) {
                        onSave(namaObat, jamMinum, selectedOption)
                    }
                },
                modifier = Modifier.fillMaxWidth().height(60.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E3A8A)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("SIMPAN JADWAL", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }

        // BAGIAN DIALOG (Diletakkan di dalam fungsi Composable utama)
        if (showTimePicker) {
            TimePickerDialog(
                onCancel = { showTimePicker = false },
                onConfirm = {
                    val jamStr = if (timePickerState.hour < 10) "0${timePickerState.hour}" else "${timePickerState.hour}"
                    val menitStr = if (timePickerState.minute < 10) "0${timePickerState.minute}" else "${timePickerState.minute}"
                    jamMinum = "$jamStr:$menitStr"
                    showTimePicker = false
                }
            ) {
                TimePicker(state = timePickerState)
            }
        }
    }
}

// FUNGSI PEMBANTU (Diletakkan di luar fungsi utama agar rapi)
@Composable
fun TimePickerDialog(
    onCancel: () -> Unit,
    onConfirm: () -> Unit,
    content: @Composable () -> Unit
) {
    AlertDialog(
        onDismissRequest = onCancel,
        confirmButton = {
            Button(onClick = onConfirm) { Text("OK") }
        },
        dismissButton = {
            TextButton(onClick = onCancel) { Text("BATAL") }
        },
        text = { content() }
    )
}