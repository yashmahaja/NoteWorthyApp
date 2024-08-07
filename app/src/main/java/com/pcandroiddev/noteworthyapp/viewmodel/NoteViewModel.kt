package com.pcandroiddev.noteworthyapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.RequestManager
import com.pcandroiddev.noteworthyapp.adapters.ImageAdapter
import com.pcandroiddev.noteworthyapp.adapters.NoteAdapter
import com.pcandroiddev.noteworthyapp.models.image.DeleteImageResponse
import com.pcandroiddev.noteworthyapp.models.jwt.RefreshTokenRequest
import com.pcandroiddev.noteworthyapp.models.note.ImgUrl
import com.pcandroiddev.noteworthyapp.models.note.NoteRequest
import com.pcandroiddev.noteworthyapp.models.note.NoteResponse
import com.pcandroiddev.noteworthyapp.repository.NoteRepository
import com.pcandroiddev.noteworthyapp.util.NetworkResults
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteRepository: NoteRepository) : ViewModel() {

    val notesLiveData: LiveData<NetworkResults<List<NoteResponse>>> get() = noteRepository.notesLiveData
    val statusLiveData: LiveData<NetworkResults<String>> get() = noteRepository.statusLiveData

    val uploadImageUrlLiveData: LiveData<NetworkResults<List<ImgUrl>>> get() = noteRepository.uploadImageUrlLiveData
    val deleteImageLiveData: LiveData<NetworkResults<DeleteImageResponse>> get() = noteRepository.deleteImageLiveData

    fun getNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.getNotes()
        }
    }

    fun sortNotesByPriority(sortBy: String) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.sortNotesByPriority(sortBy = sortBy)
        }
    }


    fun searchNotes(searchText: String) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.searchNotes(searchText = searchText)
        }
    }


    fun createNotes(
        noteRequest: NoteRequest
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.createNote(noteRequest = noteRequest)
        }
    }

    fun updateNotes(
        noteId: String,
        noteRequest: NoteRequest
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.updateNote(
                noteId = noteId,
                noteRequest = noteRequest
            )
        }
    }

    fun deleteNotes(noteId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.deleteNote(noteId = noteId)
        }
    }


    fun uploadImage(multipartBodyPartList: List<MultipartBody.Part>) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.uploadImage(multipartBodyPartList = multipartBodyPartList)
        }
    }

    fun deleteImage(publicId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.deleteImage(publicId = publicId)
        }
    }




}