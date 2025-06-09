package com.example.notebook.notelist.domain.usecases

import com.example.notebook.core.data.repository.FakeNoteRepository
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetAllNotesTest {

//    @get:Rule
//    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var fakeNoteRepository: FakeNoteRepository
    private lateinit var gatAllNotes: GetAllNotes

    @Before
    fun setup() {
        fakeNoteRepository = FakeNoteRepository()
        gatAllNotes = GetAllNotes(fakeNoteRepository)
        fakeNoteRepository.shouldHaveFilledList(true)
    }

    @Test
    fun `get notes sort by title , sorted by title`() = runTest {
        val notes = gatAllNotes.invoke(true)

        for (i in 0..notes.size - 2) {
//            assertThat(notes[i].title).isLessThan(notes[i + 1].title)
        }
    }

}