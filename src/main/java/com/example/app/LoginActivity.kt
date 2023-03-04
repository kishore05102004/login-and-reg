package com.example.app

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val edUserName : EditText = findViewById(R.id.regUuserNameSection)
        val edPassword : EditText = findViewById(R.id.regConformPasswordSection)
        val loginButton : Button = findViewById(R.id.regButton)
        val newRegister = findViewById<TextView>(R.id.registration)
        val db = Data(applicationContext, "healthcare", null, 1)
        loginButton.setOnClickListener {
            val username = edUserName.text.toString()
            val password = edPassword.text.toString()
            if ((username.isEmpty()) || (password.isEmpty())) {
                Toast.makeText(this, "fill the details!", Toast.LENGTH_SHORT).show()
            }
            else {
                if(db.login(username, password) == 1) {
                    Toast.makeText(this, "login success!", Toast.LENGTH_SHORT).show()
                    val sharedPref = getSharedPreferences("share_prefs", Context.MODE_PRIVATE)
                    val editor : SharedPreferences.Editor = sharedPref.edit()
                    editor.putString("username", username)
                    editor.apply()
                    startActivity(Intent(this, HomeActivity::class.java))
                }
                else {
                    Toast.makeText(this, "Invalid Username and Password", Toast.LENGTH_SHORT).show()
                }

            }

        }

        newRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }
}