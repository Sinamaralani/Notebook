package com.example.notebook.core.di

import android.app.Application
import androidx.room.Room
import com.example.notebook.addNote.domain.usecases.SearchImages
import com.example.notebook.addNote.domain.usecases.UpsertNote
import com.example.notebook.core.data.local.NoteDatabase
import com.example.notebook.core.data.repository.FakeAndroidNoteRepository
import com.example.notebook.core.data.repository.NoteRepositoryImpl
import com.example.notebook.core.domain.repository.ImagesRepository
import com.example.notebook.core.domain.repository.NoteRepository
import com.example.notebook.noteList.domain.usecases.DeleteNote
import com.example.notebook.noteList.domain.usecases.GetAllNotes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(application: Application): NoteDatabase {
        return Room.inMemoryDatabaseBuilder(application, NoteDatabase::class.java).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(): NoteRepository {
        return FakeAndroidNoteRepository()
    }

    @Provides
    @Singleton
    fun provideGetAllNotesUseCase(
        noteRepository: NoteRepository
    ): GetAllNotes {
        return GetAllNotes(noteRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteNoteUseCase(
        noteRepository: NoteRepository
    ): DeleteNote {
        return DeleteNote(noteRepository)
    }

    @Provides
    @Singleton
    fun provideUpsertNoteUseCase(
        noteRepository: NoteRepository
    ): UpsertNote {
        return UpsertNote(noteRepository)
    }


//    @Provides
//    @Singleton
//    fun provideImageApi(): ImagesApi {
//        return Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl(ImagesApi.BASE_URL)
//            .build()
//            .create(ImagesApi::class.java)
//    }

//    @Provides
//    @Singleton
//    fun provideImagesRepository(): ImagesRepository {
//        return FakeAndroidImagesRepository()
//    }

//    @Provides
//    @Singleton
//    fun provideSearchImagesUseCase(
//        imagesRepository: ImagesRepository
//    ): SearchImages {
//        return SearchImages(imagesRepository)
//    }

}