package com.example.notebook.addnote.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notebook.addnote.domain.usecases.SearchImages
import com.example.notebook.addnote.domain.usecases.UpsertNote
import com.example.notebook.addnote.presentation.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val upsertNote: UpsertNote,
    private val searchImages: SearchImages
) : ViewModel() {

    private val _addNoteState = MutableStateFlow(AddNoteState())
    val addNoteState = _addNoteState.asStateFlow()

    private val _noteSavedChannel = Channel<Boolean>()
    val noteSavedFlow = _noteSavedChannel.receiveAsFlow()

    private var searchJob: Job? = null

    fun onAction(actions: AddNoteActions) {
        when (actions) {
            is AddNoteActions.UpdateTitle -> {
                _addNoteState.update {
                    it.copy(title = actions.newTitle)
                }
            }

            is AddNoteActions.UpdateDescription -> {
                _addNoteState.update {
                    it.copy(description = actions.newDescription)
                }
            }

            is AddNoteActions.SaveNote -> {
                viewModelScope.launch {
                    val isSaved = upsertNote(
                        title = addNoteState.value.title,
                        description = addNoteState.value.description,
                        imageUrl = addNoteState.value.imageUrl

                    )
                    _noteSavedChannel.send(isSaved)
                }
            }

            is AddNoteActions.PickImage -> {
                _addNoteState.update {
                    it.copy(imageUrl = actions.imageUrl)
                }
            }

            is AddNoteActions.UpdateSearchImageQuery -> {
                _addNoteState.update {
                    it.copy(searchImagesQuery = actions.newQuery)
                }
                searchImage(actions.newQuery)
            }

            is AddNoteActions.UpdateImagesDialogVisibility -> {
                _addNoteState.update {
                    it.copy(isImagesDialogShowing = !it.isImagesDialogShowing)
                }
            }
        }
    }

    suspend fun upsertNote(title: String, description: String, imageUrl: String): Boolean {
        return upsertNote.invoke(title, description, imageUrl)
    }

    fun searchImage(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            searchImages.invoke(query).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _addNoteState.update {
                            it.copy(imageList = emptyList())
                        }
                    }

                    is Resource.Loading -> {
                        _addNoteState.update {
                            it.copy(isLoadingImages = result.isLoading)
                        }
                    }

                    is Resource.Success -> {
                        _addNoteState.update {
                            it.copy(imageList = result.data?.images ?: emptyList())
                        }
                    }
                }

            }
        }
    }

}