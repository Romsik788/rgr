package com.roman.rgr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database
import com.roman.rgr.databinding.ActivityNoteDetailBinding

class NoteDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteDetailBinding
    private lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        val title = intent.getStringExtra("title")
        val content = intent.getStringExtra("content")
        val noteId = intent.getStringExtra("noteId")

        binding.titleEditText.setText(title)
        binding.contentEditText.setText(content)

        binding.saveButton.setOnClickListener {
            viewModel.saveNote(
                noteId!!,
                binding.titleEditText.text.toString().trim(),
                binding.contentEditText.text.toString().trim()
            )
            finish()
        }
        binding.deleteButton.setOnClickListener {
            viewModel.deleteNote(
                noteId!!
            )
            finish()
        }
    }

}