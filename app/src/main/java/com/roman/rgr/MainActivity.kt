package com.roman.rgr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.roman.rgr.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        binding.fabAddNote.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }
        loadNotes()
    }

    private fun loadNotes() {
        val notesList = mutableListOf<Note>()
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1)

        binding.notesListView.adapter = adapter
        viewModel.loadNotes(adapter, notesList)

        binding.notesListView.setOnItemClickListener { _, _, position, _ ->
            val selectedNote = notesList[position]

            val intent = Intent(this@MainActivity, NoteDetailActivity::class.java).apply {
                putExtra("noteId", selectedNote.id)
                putExtra("title", selectedNote.title)
                putExtra("content", selectedNote.content)
            }
            startActivity(intent)
        }
    }

}
