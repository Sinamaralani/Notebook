package com.example.notebook.core.di

import android.app.Application
import androidx.room.Room
import com.example.notebook.addnote.domain.usecases.SearchImages
import com.example.notebook.addnote.domain.usecases.UpsertNote
import com.example.notebook.core.data.local.NoteDatabase
import com.example.notebook.core.data.remote.api.ImageApi
import com.example.notebook.core.data.remote.api.ImageApi.Companion.BASE_URL
import com.example.notebook.core.data.repository.ImagesRepositoryImpl
import com.example.notebook.core.data.repository.NoteRepositoryImpl
import com.example.notebook.core.domain.repository.ImagesRepository
import com.example.notebook.core.domain.repository.NoteRepository
import com.example.notebook.notelist.domain.usecases.DeleteNote
import com.example.notebook.notelist.domain.usecases.GetAllNotes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(application: Application): NoteDatabase {
        return Room.databaseBuilder(application, NoteDatabase::class.java, "note_db").build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(noteDb: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(noteDb)
    }

    @Provides
    @Singleton
    fun provideGetAllNotesUseCase(noteRepository: NoteRepository): GetAllNotes {
        return GetAllNotes(noteRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteNoteUseCase(noteRepository: NoteRepository): DeleteNote {
        return DeleteNote(noteRepository)
    }

    @Provides
    @Singleton
    fun provideUpsertNoteUseCase(noteRepository: NoteRepository): UpsertNote {
        return UpsertNote(noteRepository)
    }

    @Provides
    @Singleton
    fun provideImageApi(): ImageApi {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ImageApi::class.java)
    }

    @Provides
    @Singleton
    fun provideImageRepository(imageApi: ImageApi): ImagesRepository {
        return ImagesRepositoryImpl(imageApi)
    }

    @Provides
    @Singleton
    fun provideSearchImageUseCase(imagesRepository: ImagesRepository): SearchImages {
        return SearchImages(imagesRepository)
    }

}