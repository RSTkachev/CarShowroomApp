package com.example.carshowroom.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.carshowroom.MainActivity
import com.example.carshowroom.R
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        firebaseAuth = FirebaseAuth.getInstance()

        var emailResetPassword : EditText = findViewById(R.id.email_to_reset_password)
        var button : Button = findViewById(R.id.reset_password)

        button.setOnClickListener {
            val email = emailResetPassword.text.toString()
            if (email.isNotEmpty()) {
                firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Проверьте вашу почту, чтобы сбросить пароль", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, SignInActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        startActivity(intent)
                    }
                    else {
                        Log.i("firebaseAuth", it.exception.toString())
                        if (it.exception.toString().equals("com.google.firebase.auth.FirebaseAuthInvalidUserException: " +
                                    "There is no user record corresponding to this identifier. The user may have been deleted.")) {
                            Toast.makeText(this, "Пользователь с данным email отсутствует", Toast.LENGTH_LONG).show()
                        }
                        else if (it.exception.toString().equals("com.google.firebase.auth.FirebaseAuthInvalidCredentialsException: " +
                                    "The email address is badly formatted.")) {
                            Toast.makeText(this, "Некорректный email", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            else {
                Toast.makeText(this, "Заполните поле Email", Toast.LENGTH_SHORT).show()
            }
        }
        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed()
        return true
    }
}