package com.roman.rgr

import android.util.Log
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class NoteViewModel : ViewModel() {

    private var databaseReference: DatabaseReference = FirebaseDatabase.getInstance("https://notes-21104-default-rtdb.europe-west1.firebasedatabase.app/").getReference("notes")

    fun loadNotes(adapter: ArrayAdapter<String>, notesList: MutableList<Note>) {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                adapter.clear()
                notesList.clear()

                for (noteSnapshot in dataSnapshot.children) {
                    val note = noteSnapshot.getValue(Note::class.java)

                    if (note != null) {
                        notesList.add(note)
                        adapter.add(note.title)
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("MainActivity", "loadNotes:onCancelled", databaseError.toException())
            }
        })
    }
    fun saveNote(noteId: String?, title: String, content: String) {
        if (title.isNotEmpty() && content.isNotEmpty()) {
            var noteId = noteId ?: databaseReference.push().key ?: ""

            val updatedNote = Note(noteId, title, content)

            databaseReference.child(noteId).setValue(updatedNote)
        }
    }

    fun deleteNote(noteId: String) {
        databaseReference.child(noteId).removeValue()
    }
}
