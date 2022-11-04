package com.example.carshowroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {


    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        firebaseAuth = FirebaseAuth.getInstance()

        if (firebaseAuth.currentUser != null) {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        val butSignIn: Button = findViewById(R.id.sign_in);
        butSignIn.setOnClickListener{
            val intSignIn = Intent(this, SignInActivity::class.java);
            startActivity(intSignIn);
        }

        val butSignUp: Button = findViewById(R.id.sign_up);
        butSignUp.setOnClickListener{
            val intSignUp = Intent(this, SignUpActivity::class.java);
            startActivity(intSignUp);
        }
    }
}