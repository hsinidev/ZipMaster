package com.example.zipmaster.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zipmaster.domain.model.ArchiveJob
import com.example.zipmaster.domain.model.EntitlementState
import com.example.zipmaster.domain.repository.ZipRepository
import com.example.zipmaster.domain.usecase.CompressArchiveUseCase
import com.example.zipmaster.domain.usecase.ExtractArchiveUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ZipMasterViewModel @Inject constructor(
    private val repository: ZipRepository,
    private val compressArchiveUseCase: CompressArchiveUseCase,
    private val extractArchiveUseCase: ExtractArchiveUseCase
) : ViewModel() {

    private val _history = MutableStateFlow<List<ArchiveJob>>(emptyList())
    val history: StateFlow<List<ArchiveJob>> = _history.asStateFlow()

    private val _processingProgress = MutableStateFlow<Float?>(null)
    val processingProgress: StateFlow<Float?> = _processingProgress.asStateFlow()

    private val _entitlement = MutableStateFlow(EntitlementState.Pro)
    val entitlement: StateFlow<EntitlementState> = _entitlement.asStateFlow()

    init {
        loadHistory()
        viewModelScope.launch {
            repository.getEntitlementState().collect {
                _entitlement.value = it
            }
        }
    }

    fun loadHistory() {
        viewModelScope.launch {
            repository.getHistory().collect {
                _history.value = it
            }
        }
    }

    fun compress(files: List<Uri>, archiveName: String, format: String) {
        viewModelScope.launch {
            _processingProgress.value = 0.0f
            compressArchiveUseCase(files, archiveName, format).collect { progress ->
                _processingProgress.value = progress
            }
            _processingProgress.value = null
            loadHistory()
        }
    }

    fun extract(uri: Uri, targetPath: String) {
        viewModelScope.launch {
            _processingProgress.value = 0.0f
            extractArchiveUseCase(uri, targetPath).collect { progress ->
                _processingProgress.value = progress
            }
            _processingProgress.value = null
            loadHistory()
        }
    }

    fun purchasePremium() {
        viewModelScope.launch {
            repository.purchasePremium()
        }
    }
}
