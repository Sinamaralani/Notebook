//package com.example.notebook.core.data.local
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import androidx.test.filters.SmallTest
//import com.example.notebook.core.di.AppModule
//import dagger.hilt.android.testing.HiltAndroidRule
//import dagger.hilt.android.testing.HiltAndroidTest
//import dagger.hilt.android.testing.UninstallModules
//import kotlinx.coroutines.test.runTest
//import org.junit.After
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import javax.inject.Inject
//
//@HiltAndroidTest
//@SmallTest
//@UninstallModules(AppModule::class)
//class NoteDaoTest {
//
//    @get:Rule
//    val hiltRule = HiltAndroidRule(this)
//
//    @get:Rule
//    var instantTaskExecutorRule = InstantTaskExecutorRule()
//
//    @Inject
//    lateinit var noteDatabase: NoteDatabase
//    private lateinit var noteDao: NoteDao
//
//    @Before
//    fun setup() {
//        hiltRule.inject()
//        noteDao = noteDatabase.noteDao
//    }
//
//    @After
//    fun tearDown() {
//        noteDatabase.close()
//    }
//
//    @Test
//    fun getAllNotesFromEmptyDb_noteListIsEmpty() = runTest {
//        assert(
//            noteDao.getAllNotes().isEmpty()
//        )
//    }
//
//    @Test
//    fun getAllNotesFromDb_noteListIsNotEmpty() = runTest {
//        for (i in 1..4) {
//            val note = NoteEntity(
//                id = i,
//                title = "title$i",
//                description = "description$i",
//                imageUrl = "imageUrl$i",
//                dateAdded = System.currentTimeMillis()
//            )
//            noteDao.upsertNote(note)
//        }
//        assert(
//            noteDao.getAllNotes().isNotEmpty()
//        )
//    }
//
//    @Test
//    fun upsertNote_noteIsUpserted() = runTest {
//        val note = NoteEntity(
//            id = 1,
//            title = "title",
//            description = "description",
//            imageUrl = "imageUrl",
//            dateAdded = System.currentTimeMillis()
//        )
//        noteDao.upsertNote(note)
//        assert(
//            noteDao.getAllNotes().contains(note)
//        )
//    }
//
//    @Test
//    fun deleteNote_noteIsDeleted() = runTest {
//        val note = NoteEntity(
//            id = 1,
//            title = "title",
//            description = "description",
//            imageUrl = "imageUrl",
//            dateAdded = System.currentTimeMillis()
//        )
//        noteDao.upsertNote(note)
//        assert(
//            noteDao.getAllNotes().contains(note)
//        )
//        noteDao.deleteNote(note)
//        assert(!noteDao.getAllNotes().contains(note))
//    }
//
//}