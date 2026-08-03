package com.example.zipmaster.presentation.ui.screens

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.zipmaster.presentation.viewmodel.ZipMasterViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArchiveEditorScreen(viewModel: ZipMasterViewModel = hiltViewModel()) {
    var archName by remember { mutableStateOf("backup_workspace_2026.zip") }
    var format by remember { mutableStateOf("ZIP") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Archive Toolkit", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(24.dp))

            // File selection mock card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surface),
                contentAlignment = Alignment.Center
            ) {
                Text("Selected: 12 assets folders & documents", color = MaterialTheme.colorScheme.onSurface)
            }

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = archName,
                onValueChange = { archName = it },
                label = { Text("Archive Filename") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Format Selection
            Text("Compression Container", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.secondary)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("ZIP", "7Z", "TAR", "RAR").forEach { opt ->
                    FilterChip(
                        selected = format == opt,
                        onClick = { format = opt },
                        label = { Text(opt) }
                    )
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    viewModel.compress(
                        listOf(Uri.parse("content://media/external/file/1")),
                        archName,
                        format
                    )
                },
                modifier = Modifier.weight(1f).height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Compress", fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = {
                    viewModel.extract(
                        Uri.parse("content://media/external/file/backup_workspace_2026.zip"),
                        "/storage/emulated/0/Download"
                    )
                },
                modifier = Modifier.weight(1f).height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Text("Extract", fontWeight = FontWeight.Bold)
            }
        }
    }
}
