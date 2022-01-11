package com.example.noteappviewmodel

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteappviewmodel.NoteAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var rvAdapter: NoteAdapter
    lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        viewModel.getNotes().observe(this, {
                notes -> rvAdapter.update(notes)
        })

        submit.setOnClickListener {
            if(noteET.text.toString() !=""){
                viewModel.addNote(noteET.text.toString())
                Toast.makeText(this,"Note Added",Toast.LENGTH_SHORT)
                noteET.text.clear()
                noteET.clearFocus()
            }else{
                Toast.makeText(this,"please enter note",Toast.LENGTH_SHORT)
            }

        }

        rvAdapter = NoteAdapter(this)
        noteRecyclerView.adapter = rvAdapter
        noteRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun raiseDialog(id: Int,note:String){
        val dialogBuilder = AlertDialog.Builder(this)
        val updatedNote = EditText(this)
        updatedNote.hint = note
        dialogBuilder
            .setCancelable(false)
            .setPositiveButton("Save", DialogInterface.OnClickListener {
                    _, _ -> viewModel.editNote(id, updatedNote.text.toString())
                Toast.makeText(this,"Note Edited",Toast.LENGTH_SHORT)

            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, _ -> dialog.cancel()
            })
        val alert = dialogBuilder.create()
        alert.setTitle("Update Note")
        alert.setView(updatedNote)
        alert.show()
    }
    fun deleteDialog(id: Int){
        val dialogBuilder = AlertDialog.Builder(this)
        val textNote = TextView(this)
        textNote.text = "Delete this Note ?"
        dialogBuilder
            .setCancelable(false)
            .setPositiveButton("DELETE", DialogInterface.OnClickListener {
                    _, _ -> viewModel.deleteNote(id, )
            })
            .setNegativeButton("CANCEL", DialogInterface.OnClickListener {
                    dialog, _ -> dialog.cancel()
            })
        val alert = dialogBuilder.create()
        alert.setTitle("Delete")
        alert.setView(textNote)
        alert.show()
    }
}