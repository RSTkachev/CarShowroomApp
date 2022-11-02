package com.example.carshowroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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