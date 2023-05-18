package com.example.idle_rpg_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val context = this
        val db = DataBaseHandler(context)

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val editUsername = findViewById<EditText>(R.id.editUsername)
        val editName = findViewById<EditText>(R.id.editName)
        val editLastName = findViewById<EditText>(R.id.editLastName)
        val editEmail = findViewById<EditText>(R.id.editEmail)
        val editPassword = findViewById<EditText>(R.id.editPassword)
        val editImage = findViewById<EditText>(R.id.editImage)
        val btnRead = findViewById<Button>(R.id.btnRead)
        val btnUpdate = findViewById<Button>(R.id.btnUpdate)
        val btnDelete = findViewById<Button>(R.id.btnDelete)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        btnAdd.setOnClickListener {
            if (editUsername.text.isNotEmpty() &&
                editName.text.isNotEmpty() &&
                editLastName.text.isNotEmpty() &&
                editEmail.text.isNotEmpty() &&
                editPassword.text.isNotEmpty() &&
                editImage.text.isNotEmpty()
            ) {
                val user = User(
                    editUsername.text.toString(),
                    editName.text.toString(),
                    editLastName.text.toString(),
                    editPassword.text.toString(),
                    editEmail.text.toString(),
                    editImage.text.toString()
                )
                db.insertData(user)
            } else {
                Toast.makeText(context, "Por favor complete los datos", Toast.LENGTH_SHORT).show()
            }
        }

        btnRead.setOnClickListener {
            val data = db.readData()
            tvResult.text = ""
            for (i in 0..(data.size - 1)) {
                tvResult.append(
                    data.get(i).id.toString() + " " +
                            data.get(i).username + " " +
                            data.get(i).name + " " +
                            data.get(i).lastName + " " +
                            data.get(i).email + " " +
                            data.get(i).password + " " +
                            data.get(i).urlImage + " " +
                            data.get(i).idGuild + "\n"
                )
            }
        }
    }
}