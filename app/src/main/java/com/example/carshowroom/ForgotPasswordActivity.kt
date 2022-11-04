package com.example.carshowroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        firebaseAuth = FirebaseAuth.getInstance()

        var emailResetPassword : EditText = findViewById(R.id.email_to_reset_password)
        var button : Button = findViewById(R.id.reset_password)

        button.setOnClickListener() {
            val email = emailResetPassword.text.toString()
            if (email.isNotEmpty()) {
                firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener() {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Проверьте вашу почту, чтобы сбросить пароль", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, SignInActivity::class.java))
                    }
                    else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else {
                Toast.makeText(this, "Заполните поле Email", Toast.LENGTH_SHORT).show()
            }
        }
    }
}