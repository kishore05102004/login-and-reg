package com.example.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val regUserName = findViewById<EditText>(R.id.regUuserNameSection)
        val regEmail = findViewById<EditText>(R.id.regemailSection)
        val regPassword = findViewById<EditText>(R.id.regpasswordSection)
        val confirm = findViewById<EditText>(R.id.regConformPasswordSection)
        val regButton = findViewById<Button>(R.id.regButton)
        val loginText = findViewById<TextView>(R.id.registration)

        loginText.setOnClickListener(
            View.OnClickListener {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }

        )

        regButton.setOnClickListener(
            View.OnClickListener {
                val username = regUserName.text.toString()
                val email = regEmail.text.toString()
                val password = regPassword.text.toString()
                val conform = confirm.text.toString()
                val db = Data(applicationContext, "healthcare", null, 1)


                if(username.isEmpty() || email.isEmpty() || password.isEmpty() || conform.isEmpty()) {
                    Toast.makeText(this, "Please fill the details", Toast.LENGTH_SHORT).show()
                }
                else {
                    // comparing two passwords are same using compareTo
                    if(password.compareTo(conform) == 0) {
                        if(isValid(password)){
                            db.register(username, email, password)
                            Toast.makeText(this, "Record Inserted", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                        }
                        else {
                            Toast.makeText(this, "Password must contain at least 8 characters, One UpperCase letter, digit and special symbol", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else {
                        Toast.makeText(this, "password and confirm password didn't match", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        )

    }
    private fun isValid(password : String) : Boolean {
        val passwordRegex = Regex("^(?=.*[A-Z])(?=.*[!@#\$&*])(?=.*[0-9])(?=.{9,17}).*$")
        return password.matches(passwordRegex)
    }
}

