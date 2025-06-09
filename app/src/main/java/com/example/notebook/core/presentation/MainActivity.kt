package com.example.notebook.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notebook.addNote.presentation.AddNoteScreen
import com.example.notebook.core.presentation.ui.theme.NotebookTheme
import com.example.notebook.noteList.presentation.NoteListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotebookTheme {
                Navigation()
            }
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        modifier = Modifier.statusBarsPadding(),
        navController = navController,
        startDestination = Screen.NoteList
    ) {

        composable<Screen.NoteList> {
            NoteListScreen(onNavigateToAddNote = { navController.navigate(Screen.AddNote) })
        }

        composable<Screen.AddNote> {
            AddNoteScreen(onSave = {navController.popBackStack()})
        }
    }

}

